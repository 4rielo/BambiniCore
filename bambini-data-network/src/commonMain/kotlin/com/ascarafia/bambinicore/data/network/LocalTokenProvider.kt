package com.ascarafia.bambinicore.data.network

import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.network.TokenProvider

class LocalTokenProvider(
    private val accountLocalDatasource: AccountDataSource
) : TokenProvider {

    override suspend fun getAccessToken() =
        accountLocalDatasource.getToken()

    override suspend fun getRefreshToken() =
        accountLocalDatasource.getRefreshToken()

    override suspend fun saveTokens(
        accessToken: String,
        refreshToken: String
    ) {
        accountLocalDatasource.saveToken(accessToken)
        accountLocalDatasource.saveRefreshToken(refreshToken)
    }

    override suspend fun clearTokens() {
        accountLocalDatasource.saveToken("")
        accountLocalDatasource.saveRefreshToken("")
    }
}