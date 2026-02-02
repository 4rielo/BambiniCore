package com.ascarafia.bambinicore.domain.datasource

interface AccountDataSource {

    fun saveToken(token: String)

    fun getToken(): String?

    fun saveRefreshToken(refreshToken: String)

    fun getRefreshToken(): String?

    fun saveLastUpdate(lastUpdate: String)

    fun getLastUpdate(): String?

    fun getAccountId(): String?

    fun saveAccountId(accountId: String)
}