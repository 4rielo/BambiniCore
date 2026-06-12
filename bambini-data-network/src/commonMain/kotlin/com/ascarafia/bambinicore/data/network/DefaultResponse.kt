package com.ascarafia.bambinicore.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultResponse<T>(
    @SerialName("responseCode") val responseCode: Int? = null,
    @SerialName("responseMessage") val responseMessage: String? = null,
    @SerialName("responseBody") val data: T? = null
)
