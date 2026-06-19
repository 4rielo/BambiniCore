package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class StudyDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("patientId") val patientId: String? = null,
    @SerialName("doctorId") val doctorId: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("result") val result: String? = null,
    @SerialName("date") val date: Instant? = null,
    @SerialName("attachments") val attachments: List<String>? = null,
    @SerialName("createdAt") val createdAt: Instant? = null
)
