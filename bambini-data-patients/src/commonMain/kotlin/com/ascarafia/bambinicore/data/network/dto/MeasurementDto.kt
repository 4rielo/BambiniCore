package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasurementDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("patientId") val patientId: String? = null,
    @SerialName("doctorId") val doctorId: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("value") val value: Double? = null,
    @SerialName("unit") val unit: String? = null,
    @SerialName("measuredAt") val measuredAt: String? = null,
    @SerialName("createdAt") val createdAt: String? = null
)
