package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.PatientDto
import com.ascarafia.bambinicore.domain.model.Patient

fun PatientDto.toPatient(): Patient {
    return Patient(
        patientId = patientId.orEmpty(),
        idNumber = idNumber.orEmpty(),
        lastUpdated = lastUpdated.orEmpty(),
        isDeleted = isDeleted == "true",
        name = name.orEmpty(),
        lastName = lastName.orEmpty(),
        dateOfBirth = dateOfBirth.orEmpty(),
        gestationWeeks = gestationWeeks.orEmpty(),
        gender = gender.orEmpty(),
        city = city.orEmpty(),
        province = province.orEmpty(),
        country = country.orEmpty(),
        socialSecurity = socialSecurity.orEmpty(),
        socialSecurityNumber = socialSecurityNumber.orEmpty(),
        legalGuardianA = legalGuardianA.orEmpty(),
        legalGuardianARelationship = legalGuardianARelationship.orEmpty(),
        phoneNumberA = phoneNumberA.orEmpty(),
        legalGuardianB = legalGuardianB,
        legalGuardianBRelationship = legalGuardianBRelationship,
        phoneNumberB = phoneNumberB,
        address = address,
        email = email,
        specialComment = specialComment,
        familyHistory = familyHistory,
        allergies = allergies.orEmpty(),
        medications = medications.orEmpty(),
        clinicHistory = clinicHistory.orEmpty().map { it.toClinicHistory() },
        otherStudies = otherStudies.orEmpty().map { it.toClinicHistory() },
        size = size.orEmpty().map { it.toSpecialMeasurement() },
        weight = weight.orEmpty().map { it.toSpecialMeasurement() },
        headSize = headSize.orEmpty().map { it.toSpecialMeasurement() },
        bmi = bmi.orEmpty().map { it.toSpecialMeasurement() }
    )
}

fun Patient.toPatientDto(): PatientDto {
    return PatientDto(
        patientId = patientId,
        idNumber = idNumber,
        lastUpdated = lastUpdated,
        isDeleted = isDeleted.toString(),
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
        legalGuardianA = legalGuardianA,
        legalGuardianARelationship = legalGuardianARelationship,
        phoneNumberA = phoneNumberA,
        legalGuardianB = legalGuardianB,
        legalGuardianBRelationship = legalGuardianBRelationship,
        phoneNumberB = phoneNumberB,
        address = address,
        email = email,
        specialComment = specialComment,
        familyHistory = familyHistory,
        allergies = allergies,
        medications = medications,
        clinicHistory = clinicHistory.map { it.toClinicHistoryDto() },
        otherStudies = otherStudies.map { it.toClinicHistoryDto() },
        size = size.map { it.toSpecialMeasurementDto() },
        weight = weight.map { it.toSpecialMeasurementDto() },
        headSize = headSize.map { it.toSpecialMeasurementDto() },
        bmi = bmi.map { it.toSpecialMeasurementDto() }
    )
}