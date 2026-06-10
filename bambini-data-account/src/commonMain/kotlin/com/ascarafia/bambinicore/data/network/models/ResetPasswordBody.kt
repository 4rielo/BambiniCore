package com.ascarafia.bambini.data.network.model

import kotlinx.serialization.SerialName

data class ResetPasswordBody(
    @SerialName("token") val token: String = ""
)
