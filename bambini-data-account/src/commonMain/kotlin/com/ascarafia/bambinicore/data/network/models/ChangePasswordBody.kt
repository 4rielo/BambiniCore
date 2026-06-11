package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.SerialName

data class ChangePasswordBody(
    @SerialName("old_password") val oldPassword: String = "",
    @SerialName("new_password") val newPassword: String = ""
)
