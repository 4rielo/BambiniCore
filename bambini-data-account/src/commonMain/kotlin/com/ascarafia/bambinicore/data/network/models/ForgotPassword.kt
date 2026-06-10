package com.ascarafia.bambini.data.network.model

import kotlinx.serialization.SerialName

data class ForgotPassword(
    @SerialName("email") val email: String = ""
)
