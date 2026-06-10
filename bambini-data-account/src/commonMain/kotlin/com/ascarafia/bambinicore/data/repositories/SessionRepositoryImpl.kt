package com.ascarafia.bambini.data.repositories

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.error.DataError
import com.ascarafia.bambinicore.domain.model.Account
import com.ascarafia.bambini.domain.network.model.LoginResponse
import com.ascarafia.bambini.domain.network.model.RegisterRequest
import com.ascarafia.bambini.domain.repositories.SessionRepository
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.datasource.AuthDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SessionRepositoryImpl(
    private val accountDataSource: AccountDataSource,
    private val remoteAuthDataSource: AuthDataSource,
    private val repositoryDispatcher: CoroutineDispatcher = Dispatchers.IO
): SessionRepository {

    private val FIVE_MINUTES = 5 * 60 * 1000L

    private var refreshTokenJob: Job? = null

    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse, BambiniError> = withContext(repositoryDispatcher) {
        val response = remoteAuthDataSource.login(email, password)
        if(response is Result.Success) {
            accountDataSource.saveToken(response.data.token)
            accountDataSource.saveRefreshToken(response.data.refreshToken)
        }
        return@withContext response
    }

    override suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): Result<LoginResponse, BambiniError> = withContext(repositoryDispatcher) {
        val registerBody = RegisterRequest(
            name = name,
            lastName = lastName,
            email = email,
            password = password
        )
        val response = remoteAuthDataSource.register(registerBody)
        if(response is Result.Success) {
            accountDataSource.saveToken(response.data.token)
            accountDataSource.saveRefreshToken(response.data.refreshToken)
        }
        return@withContext response
    }

    override suspend fun refreshToken(): Result<LoginResponse, BambiniError> = withContext(repositoryDispatcher) {
        val refreshToken = accountDataSource.getRefreshToken()
        val response: Result<LoginResponse, BambiniError> = refreshToken?.let {
            remoteAuthDataSource.refreshToken(it)
        } ?: Result.Error(DataError.Remote.NO_TOKEN)

        if(response is Result.Success) {
            accountDataSource.saveToken(response.data.token)
            accountDataSource.saveRefreshToken(response.data.refreshToken)
        }

        return@withContext response
    }

    override suspend fun forgotPassword(email: String): Result<Unit, BambiniError> = withContext(repositoryDispatcher) {
        return@withContext remoteAuthDataSource.forgotPassword(email)
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Result<Unit, BambiniError> = withContext(repositoryDispatcher) {
        return@withContext remoteAuthDataSource.changePassword(oldPassword, newPassword)
    }

    override suspend fun resetPassword(
        token: String,
        newPassword: String
    ): Result<Unit, BambiniError> = withContext(repositoryDispatcher) {
        return@withContext remoteAuthDataSource.resetPassword(token, newPassword)
    }

    override suspend fun fetchAccountInfo(): Result<Account, BambiniError> {
        val token = accountDataSource.getToken()
        token?.let {
            return withContext(repositoryDispatcher) {
                val response = remoteAuthDataSource.accountInfo(token)

                if (response is Result.Success) {
                    accountDataSource.saveAccountId(response.data.id)
                }

                return@withContext response
            }
        }
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    override suspend fun logOut(): EmptyResult<BambiniError> = withContext(repositoryDispatcher) {
        val response = remoteAuthDataSource.logout()

        if (response is Result.Success) {
            accountDataSource.saveAccountId("")
            accountDataSource.saveToken("")
            accountDataSource.saveRefreshToken("")
            //TODO: logout
            return@withContext Result.Success(Unit)
        }

        return@withContext Result.Error(DataError.Remote.UNKNOWN)
    }

    override suspend fun deleteAccount(): EmptyResult<BambiniError> {
        val token = accountDataSource.getToken()
        val clientId = accountDataSource.getAccountId()

        return if(token != null && clientId != null) {
            remoteAuthDataSource.deleteAccount(token, clientId)
            //TODO: logout
        } else {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }


    override fun refreshTokenFailed() {
        refreshTokenJob?.cancel()
        refreshTokenJob = CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(FIVE_MINUTES)
                val response = refreshToken()
                if(response is Result.Error) {
                    //TODO: Handle error, retry
                    if(response.error == DataError.Remote.UNAUTHORIZED) {
                        logOut()
                    }
                } else {
                    cancelJob()
                    break
                }
            }
        }
        //TODO: Launch timer to check on refresh token
    }

    private fun cancelJob() {
        refreshTokenJob?.cancel()
        refreshTokenJob = null
    }
}