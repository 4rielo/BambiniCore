package com.ascarafia.bambinicore.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class DeleteAccountBody(
    val email: String,
    val userId: String
)
