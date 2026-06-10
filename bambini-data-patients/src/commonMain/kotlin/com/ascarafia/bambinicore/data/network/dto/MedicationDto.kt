package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicationDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("patientId") val patientId: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("dose") val dose: String? = null,
    @SerialName("notes") val notes: String? = null,
    @SerialName("startDate") val startDate: String? = null,
    @SerialName("endDate") val endDate: String? = null,
    @SerialName("createdAt") val createdAt: String? = null
)
