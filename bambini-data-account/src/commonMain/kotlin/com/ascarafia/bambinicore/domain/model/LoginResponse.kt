package com.ascarafia.bambinicore.domain.model

data class LoginResponse(
    val token: String? = null,
    val refreshToken: String? = null
)