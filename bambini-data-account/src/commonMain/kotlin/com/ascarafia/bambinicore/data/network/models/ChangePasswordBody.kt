package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.SerialName

data class ChangePasswordBody(
    @SerialName("userId") val userId: String = "",
    @SerialName("currentPassword") val currentPassword: String = "",
    @SerialName("newPassword") val newPassword: String = ""
)
