package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class GuardianDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("patientId") val patientId: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("lastName") val lastName: String? = null,
    @SerialName("relationship") val relationship: String? = null,
    @SerialName("phoneNumber") val phoneNumber: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("createdAt") val createdAt: Instant? = null
)
