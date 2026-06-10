package com.ascarafia.bambini.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(
    @SerialName("refreshToken") val refreshToken: String
)
