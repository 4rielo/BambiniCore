package com.ascarafia.bambinicore.data.repositories

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.error.DataError
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import com.ascarafia.bambinicore.domain.model.Account
import com.ascarafia.bambinicore.domain.repositories.AccountRepository
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.datasource.AuthDataSource

class AccountRepositoryImpl(
    private val accountDataSource: AccountDataSource,
    private val remoteAuthDataSource: AuthDataSource
): AccountRepository {
    override fun accountUpdated() {
        //TODO: Check whether there was some repository update
        accountDataSource.saveLastUpdate(DateTimeUtils.getCurrentDateTimeString())
    }

    override fun getAccountId(): String? {
        return accountDataSource.getAccountId()
    }

    override fun getToken(): String {
        return accountDataSource.getToken() ?: ""
    }

    override suspend fun fetchAccountInfo(): Result<Account, BambiniError> {
        val token = accountDataSource.getToken()
        token?.let {
            val accountInfo = remoteAuthDataSource.accountInfo(token)
            if(accountInfo is Result.Success) {
                accountDataSource.saveAccountId(accountInfo.data.id)
            }
            return accountInfo
        }
        return Result.Error(DataError.Remote.UNKNOWN)
    }
}