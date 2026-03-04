package com.ascarafia.bambinicore.data.database.mappers

import com.ascarafia.bambinicore.data.database.PatientEntity
import com.ascarafia.bambinicore.domain.model.Patient

fun Patient.toPatientEntity(): PatientEntity {
    return PatientEntity(
        patientId = patientId,
        idNumber = idNumber,
        lastUpdated = lastUpdated,
        isDeleted = isDeleted,
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
        clinicHistory = clinicHistory.map { it.toClinicHistoryEntity() },
        otherStudies = otherStudies.map { it.toClinicHistoryEntity() },
        size = size.map { it.toSpecialMeasurementEntity() },
        weight = weight.map { it.toSpecialMeasurementEntity() },
        headSize = headSize.map { it.toSpecialMeasurementEntity() },
        bmi = bmi.map { it.toSpecialMeasurementEntity() }
    )
}

fun PatientEntity.toPatient(): Patient {
    return Patient(
        patientId = patientId,
        idNumber = idNumber,
        lastUpdated = lastUpdated,
        isDeleted = isDeleted,
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
        clinicHistory = clinicHistory.map { it.toClinicHistory() },
        otherStudies = otherStudies.map { it.toClinicHistory() },
        size = size.map { it.toSpecialMeasurement() },
        weight = weight.map { it.toSpecialMeasurement() },
        headSize = headSize.map { it.toSpecialMeasurement() },
        bmi = bmi.map { it.toSpecialMeasurement() }
    )
}