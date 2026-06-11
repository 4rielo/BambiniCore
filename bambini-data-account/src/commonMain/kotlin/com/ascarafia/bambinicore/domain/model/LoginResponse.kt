package com.ascarafia.bambinicore.domain.model

data class LoginResponse(
    val token: String,
    val refreshToken: String
)