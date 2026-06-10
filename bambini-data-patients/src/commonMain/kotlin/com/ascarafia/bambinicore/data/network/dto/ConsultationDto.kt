package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConsultationDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("patientId") val patientId: String? = null,
    @SerialName("doctorId") val doctorId: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("reason") val reason: String? = null,
    @SerialName("diagnosis") val diagnosis: String? = null,
    @SerialName("treatment") val treatment: String? = null,
    @SerialName("notes") val notes: String? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null
)
