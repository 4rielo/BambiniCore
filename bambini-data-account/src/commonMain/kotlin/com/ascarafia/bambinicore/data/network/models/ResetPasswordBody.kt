package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.SerialName

data class ResetPasswordBody(
    @SerialName("token") val token: String = ""
)
