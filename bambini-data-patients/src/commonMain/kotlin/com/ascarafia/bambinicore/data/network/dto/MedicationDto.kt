package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class MedicationDto(
    @SerialName("id") val id: String? = null,
    @SerialName("patientId") val patientId: String? = null,
    @SerialName("doctorId") val doctorId: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("dose") val dose: String? = null,
    @SerialName("notes") val notes: String? = null,
    @SerialName("startDate") val startDate: Instant? = null,
    @SerialName("endDate") val endDate: Instant? = null,
    @SerialName("createdAt") val createdAt: Instant? = null
)
