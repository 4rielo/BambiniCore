package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordBody(
    @SerialName("userId") val userId: String = "",
    @SerialName("currentPassword") val currentPassword: String = "",
    @SerialName("newPassword") val newPassword: String = ""
)
