package com.ascarafia.bambinicore.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultResponse<T>(
    @SerialName("responseCode") val responseCode: Int?,
    @SerialName("responseMessage") val responseMessage: String?,
    @SerialName("responseBody") val data: T?
)
