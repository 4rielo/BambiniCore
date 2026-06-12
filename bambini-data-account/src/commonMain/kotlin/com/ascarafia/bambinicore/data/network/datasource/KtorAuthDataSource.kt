package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.mappers.toLoginResponse
import com.ascarafia.bambinicore.data.mappers.toRegisterRequestDto
import com.ascarafia.bambinicore.data.network.models.ChangePasswordBody
import com.ascarafia.bambinicore.data.network.models.ForgotPassword
import com.ascarafia.bambinicore.data.network.models.LoginBody
import com.ascarafia.bambinicore.data.network.dto.LoginResponseDto
import com.ascarafia.bambinicore.data.network.models.RefreshToken
import com.ascarafia.bambinicore.data.network.models.ResetPasswordBody
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.Account
import com.ascarafia.bambinicore.domain.model.LoginResponse
import com.ascarafia.bambinicore.domain.model.RegisterRequest
import com.ascarafia.bambinicore.data.mappers.toAccount
import com.ascarafia.bambinicore.data.network.dto.UserDto
import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.domain.datasource.AuthDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class KtorAuthDataSource(
    private val config: BambiniRemoteConfig,
    private val httpClient: HttpClient,
): AuthDataSource {
    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse, BambiniError> {
        val response: Result<LoginResponseDto, BambiniError> = safeCall {
            httpClient.post (
                urlString = "${config.baseUrl}/api/auth/login",
            ) {
                setBody (LoginBody(email, password))
            }
        }

        return when (response) {
            is Result.Success -> Result.Success(response.data.toLoginResponse())
            is Result.Error<*> -> response
        }
    }

    override suspend fun register(registerBody: RegisterRequest): Result<Unit, BambiniError> {
        val response: Result<Unit, BambiniError> = safeCall {
            httpClient.post (
                urlString = "${config.baseUrl}/api/auth/signup",
            ) {
                setBody (registerBody.toRegisterRequestDto())
            }
        }
        return response
    }

    override suspend fun refreshToken(refreshToken: String): Result<LoginResponse, BambiniError> {
        val refreshBody = RefreshToken(refreshToken)
        val response: Result<LoginResponseDto, BambiniError> = safeCall {
            httpClient.post (
                urlString = "${config.baseUrl}/api/auth/refresh",
            ) {
                setBody (refreshBody)
            }
        }

        return when (response) {
            is Result.Success -> Result.Success(response.data.toLoginResponse())
            is Result.Error<*> -> response
        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit, BambiniError> {
        val forgotPasswordBody = ForgotPassword(email)
        val response: Result<Unit, BambiniError> = safeCall {
            httpClient.post (
                urlString = "${config.baseUrl}/api/auth/forgot-password",
            ) {
                setBody (forgotPasswordBody)
            }
        }
        return when (response) {
            is Result.Success -> Result.Success(response.data)
            is Result.Error<*> -> response
        }
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Result<Unit, BambiniError> {
        val body = ChangePasswordBody(oldPassword, newPassword)
        val response: Result<Unit, BambiniError> = safeCall {
            httpClient.post(
                urlString = "${config.baseUrl}/api/auth/change-password"
            ) {
                setBody(body)
            }
        }
        return when(response) {
            is Result.Success -> Result.Success<Unit>(Unit)
            is Result.Error<*> -> response
        }
    }

    override suspend fun resetPassword(
        token: String,
        newPassword: String
    ): Result<Unit, BambiniError> {
        val resetPasswordBody = ResetPasswordBody(token)

        val response: Result<Unit, BambiniError> = safeCall {
            httpClient.post(
                urlString = "${config.baseUrl}/api/auth/reset-password"
            ) {
                setBody(resetPasswordBody)
            }
        }
        return when(response) {
            is Result.Success -> Result.Success<Unit>(Unit)
            is Result.Error<*> -> response
        }
    }

    override suspend fun logout(): Result<Unit, BambiniError> {

        return Result.Success(Unit)
        //TODO("Not yet implemented")
    }

    override suspend fun accountInfo(token: String): Result<Account, BambiniError> {
        val response: Result<UserDto, BambiniError> = safeCall {
            httpClient.get (
                urlString = "${config.baseUrl}/api/auth/account",
            ) {
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }

        return when (response) {
            is Result.Success -> Result.Success(response.data.toAccount())
            is Result.Error<*> -> response
        }
    }

    override suspend fun deleteAccount(clientId: String, token: String): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete (
                urlString = "${config.baseUrl}/api/auth/delete/${clientId}",
            ) {
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }
}