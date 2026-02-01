package com.ascarafia.bambinicore.domain.datasource

interface AccountDataSource {

    suspend fun saveToken(token: String)

    suspend fun getToken(): String?

    suspend fun saveRefreshToken(refreshToken: String)

    suspend fun getRefreshToken(): String?

    suspend fun saveLastUpdate(lastUpdate: String)

    suspend fun getLastUpdate(): String?

    suspend fun getAccountId(): String?

    suspend fun saveAccountId(accountId: String)
}