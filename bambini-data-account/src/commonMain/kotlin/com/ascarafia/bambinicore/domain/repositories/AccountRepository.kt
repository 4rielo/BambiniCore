package com.ascarafia.bambinicore.domain.repositories

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.Account

interface AccountRepository {
    //TODO: add methods to get/update account status and settings
    fun accountUpdated()
    fun getAccountId(): String?

    fun getToken(): String

    suspend fun fetchAccountInfo(): Result<Account, BambiniError>
}