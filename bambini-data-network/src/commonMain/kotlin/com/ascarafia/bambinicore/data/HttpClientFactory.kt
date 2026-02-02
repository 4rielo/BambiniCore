package com.ascarafia.bambinicore.data

import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(
        engine: HttpClientEngine,
        config: BambiniRemoteConfig
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
                        println("********************** API CALL LOGGER: $message")
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Any)
            }
        }
    }
}