package com.ascarafia.bambini.data.network.model

import kotlinx.serialization.SerialName

data class ChangePasswordBody(
    @SerialName("old_password") val oldPassword: String = "",
    @SerialName("new_password") val newPassword: String = ""
)
