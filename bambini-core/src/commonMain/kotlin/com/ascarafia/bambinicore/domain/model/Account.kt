package com.ascarafia.bambinicore.domain.model

import kotlin.time.Instant

data class Account(
    val id: String,
    val tenantId: String,
    val email: String,
    val emailVerified: Boolean,
    val passwordHash: String,
    val firstName: String,
    val lastName: String,
    val userPhotoUrl: String? = null,
    val role: UserRole,
    val isActive: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
    val lastLoginAt: Instant? = null,
)

enum class UserRole {
    OWNER,
    DOCTOR,
    ASSISTANT,
    RECEPTIONIST,
    PARENT
}
