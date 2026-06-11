package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.SerialName

data class ForgotPassword(
    @SerialName("email") val email: String = ""
)
