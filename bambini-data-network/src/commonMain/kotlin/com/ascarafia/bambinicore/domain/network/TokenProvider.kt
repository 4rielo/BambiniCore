package com.ascarafia.bambinicore.domain.network

interface TokenProvider {
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String
    )
    suspend fun clearTokens()
}