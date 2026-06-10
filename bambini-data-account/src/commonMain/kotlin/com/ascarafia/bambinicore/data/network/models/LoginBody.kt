package com.ascarafia.bambini.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)
