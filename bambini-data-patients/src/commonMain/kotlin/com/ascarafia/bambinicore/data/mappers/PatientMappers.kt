package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.PatientDto
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import kotlin.time.Instant

fun PatientDto.toPatient(): Patient {
    return Patient(
        id = id.orEmpty(),
        idNumber = idNumber.orEmpty(),
        name = name.orEmpty(),
        lastName = lastName.orEmpty(),
        dateOfBirth = dateOfBirth.orEmpty(),
        gestationWeeks = gestationWeeks,
        gender = gender.orEmpty(),
        city = city.orEmpty(),
        province = province.orEmpty(),
        country = country.orEmpty(),
        socialSecurity = socialSecurity,
        socialSecurityNumber = socialSecurityNumber,
        address = address,
        email = email,
        specialComment = specialComment,
        familyHistory = familyHistory,
        isDeleted = isDeleted ?: false,
        updatedAt = updatedAt?.let { DateTimeUtils.fromIsoString(it) } ?: Instant.DISTANT_PAST,
        measurements = emptyList(),
        consultations = emptyList(),
        studies = emptyList(),
        allergies = emptyList(),
        medications = emptyList(),
        guardians = emptyList()
    )
}

fun Patient.toPatientDto(tenantId: String): PatientDto {
    return PatientDto(
        id = id,
        tenantId = tenantId,
        idNumber = idNumber,
        name = name,
        lastName = lastName,
        dateOfBirth = dateOfBirth,
        gestationWeeks = gestationWeeks,
        gender = gender,
        city = city,
        province = province,
        country = country,
        socialSecurity = socialSecurity,
        socialSecurityNumber = socialSecurityNumber,
        address = address,
        email = email,
        specialComment = specialComment,
        familyHistory = familyHistory,
        isDeleted = isDeleted,
    )
}