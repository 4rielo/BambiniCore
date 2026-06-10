package com.ascarafia.bambini.domain.network.model

data class RegisterRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
)
