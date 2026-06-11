package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(
    @SerialName("refreshToken") val refreshToken: String
)
