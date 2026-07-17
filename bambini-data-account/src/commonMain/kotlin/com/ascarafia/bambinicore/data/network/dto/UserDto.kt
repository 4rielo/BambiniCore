package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val id: String? = null,
    @SerialName("tenantId") val tenantId: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("emailVerified") val emailVerified: Boolean? = null,
    @SerialName("passwordHash") val passwordHash: String? = null,
    @SerialName("firstName") val firstName: String? = null,
    @SerialName("lastName") val lastName: String? = null,
    @SerialName("userPhotoUrl") val userPhotoUrl: String? = null,
    @SerialName("role") val role: String? = null,
    @SerialName("accountType") val accountType: String? = null,
    @SerialName("isActive") val isActive: Boolean? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null,
    @SerialName("lastLoginAt") val lastLoginAt: String? = null
)
