package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllergyDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("patientId") val patientId: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("createdAt") val createdAt: String? = null
)
