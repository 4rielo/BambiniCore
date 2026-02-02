package com.ascarafia.bambinicore.domain

enum class Environment {
    DEV, STAGING, PROD
}

data class BambiniRemoteConfig(
    val baseUrls: Map<Environment, String>,
    val environment: Environment,
    val timeoutMillis: Long = 30_000,
    val enableLogging: Boolean = false
) {
    val baseUrl: String
        get() = baseUrls.getValue(environment)
}