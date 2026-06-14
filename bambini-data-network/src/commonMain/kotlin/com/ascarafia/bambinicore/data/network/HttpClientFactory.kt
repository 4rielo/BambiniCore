package com.ascarafia.bambinicore.data.network

import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.domain.Environment
import com.ascarafia.bambinicore.domain.LanguageProvider
import com.ascarafia.bambinicore.domain.network.TokenProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(
        engine: HttpClientEngine,
        config: BambiniRemoteConfig = BambiniRemoteConfig( mapOf(Environment.DEV to ""), Environment.DEV),
        tokenProvider: TokenProvider,
        languageProvider: LanguageProvider,
    ): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = config.timeoutMillis
                connectTimeoutMillis = config.timeoutMillis
                socketTimeoutMillis = config.timeoutMillis
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("******************************\nAPI CALL LOGGER:\n$message\n******************************")
                    }
                }
                level = LogLevel.ALL
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = tokenProvider.getAccessToken()
                        val refreshToken = tokenProvider.getRefreshToken()
                        if (accessToken.isNullOrBlank()) return@loadTokens null
                        BearerTokens(accessToken, refreshToken.orEmpty())
                    }
                }
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Any)
                header("language", languageProvider.getLanguage())
            }
        }
    }
}
