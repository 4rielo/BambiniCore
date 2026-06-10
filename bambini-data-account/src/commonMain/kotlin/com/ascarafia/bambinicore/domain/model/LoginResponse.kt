package com.ascarafia.bambini.domain.network.model

data class LoginResponse(
    val token: String,
    val refreshToken: String
)