package com.ascarafia.bambinicore.data.network

import com.ascarafia.bambinicore.domain.model.error.BambiniError
import kotlinx.serialization.Serializable

@Serializable
data class ServerError(
    val message: String? = null
): BambiniError
