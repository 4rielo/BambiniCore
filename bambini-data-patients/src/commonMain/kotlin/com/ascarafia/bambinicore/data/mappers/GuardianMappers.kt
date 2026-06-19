package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.GuardianDto
import com.ascarafia.bambinicore.domain.model.Guardian

fun GuardianDto.toGuardian(): Guardian {
    return Guardian(
        id = id.orEmpty(),
        patientId = patientId.orEmpty(),
        name = name.orEmpty(),
        lastName = lastName.orEmpty(),
        relationship = relationship.orEmpty(),
        phoneNumber = phoneNumber.orEmpty(),
        email = email
    )
}

fun Guardian.toGuardianDto(): GuardianDto {
    return GuardianDto(
        id = id,
        patientId = patientId,
        name = name,
        lastName = lastName,
        relationship = relationship,
        phoneNumber = phoneNumber,
        email = email,
    )
}
