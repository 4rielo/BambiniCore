package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.UserDto
import com.ascarafia.bambinicore.domain.model.Account
import com.ascarafia.bambinicore.domain.model.UserRole
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import kotlin.time.Instant

fun UserDto.toAccount(): Account {
    return Account(
        id = id.orEmpty(),
        tenantId = tenantId.orEmpty(),
        email = email.orEmpty(),
        emailVerified = emailVerified ?: false,
        passwordHash = passwordHash.orEmpty(),
        firstName = firstName.orEmpty(),
        lastName = lastName.orEmpty(),
        userPhotoUrl = userPhotoUrl,
        role = try {
            UserRole.valueOf(role.orEmpty())
        } catch (e: Exception) {
            UserRole.PARENT
        },
        accountType = accountType.orEmpty(),
        isActive = isActive ?: false,
        createdAt = createdAt?.let { DateTimeUtils.fromIsoString(it) } ?: Instant.DISTANT_PAST,
        updatedAt = updatedAt?.let { DateTimeUtils.fromIsoString(it) } ?: Instant.DISTANT_PAST,
        lastLoginAt = lastLoginAt?.let { DateTimeUtils.fromIsoString(it) }
    )
}

fun Account.toUserDto(): UserDto {
    return UserDto(
        id = id,
        tenantId = tenantId,
        email = email,
        emailVerified = emailVerified,
        passwordHash = passwordHash,
        firstName = firstName,
        lastName = lastName,
        userPhotoUrl = userPhotoUrl,
        role = role.name,
        accountType = accountType,
        isActive = isActive,
        createdAt = createdAt.toString(),
        updatedAt = updatedAt.toString(),
        lastLoginAt = lastLoginAt?.toString()
    )
}
