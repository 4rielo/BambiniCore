package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.SerialName

data class ResetPasswordBody(
    @SerialName("newPassword") val newPassword: String = ""
)
