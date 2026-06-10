package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatientDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("idNumber") val idNumber: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("lastName") val lastName: String? = null,
    @SerialName("dateOfBirth") val dateOfBirth: String? = null,
    @SerialName("gestationWeeks") val gestationWeeks: Int? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("city") val city: String? = null,
    @SerialName("province") val province: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("socialSecurity") val socialSecurity: String? = null,
    @SerialName("socialSecurityNumber") val socialSecurityNumber: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("specialComment") val specialComment: String? = null,
    @SerialName("familyHistory") val familyHistory: String? = null,
    @SerialName("isDeleted") val isDeleted: Boolean? = null,
    @SerialName("deletedAt") val deletedAt: String? = null,
    @SerialName("deletedBy") val deletedBy: String? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null
)
