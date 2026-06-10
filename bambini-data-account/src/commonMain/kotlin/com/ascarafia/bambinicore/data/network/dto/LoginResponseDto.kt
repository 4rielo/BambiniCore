package com.ascarafia.bambini.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("accessToken") val token: String?,
    @SerialName("refreshToken") val refreshToken: String?
)
