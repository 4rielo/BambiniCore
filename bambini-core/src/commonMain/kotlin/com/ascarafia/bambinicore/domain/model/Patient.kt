package com.ascarafia.bambinicore.domain.model

data class Patient(
    val patientId: String,
    val idNumber: String,
    val lastUpdated: String,
    val isDeleted: Boolean = false,
    val name: String,
    val lastName: String,
    val dateOfBirth: String,
    val gestationWeeks: String,
    val gender: String,
    val city: String,
    val province: String,
    val country: String,
    val socialSecurity: String,
    val socialSecurityNumber: String,
    val legalGuardianA: String,
    val legalGuardianARelationship: String,
    val phoneNumberA: String,
    val legalGuardianB: String? = null,
    val legalGuardianBRelationship: String? = null,
    val phoneNumberB: String? = null,
    val address: String? = null,
    val email: String? = null,
    val specialComment: String? = null,
    val familyHistory: String? = null,
    val allergies: List<String> = emptyList(),
    val medications: List<String> = emptyList(),
    val clinicHistory: List<ClinicHistory> = emptyList(),
    val otherStudies: List<ClinicHistory> = emptyList(),
    val size: List<SpecialMeasurement> = emptyList(),
    val weight: List<SpecialMeasurement> = emptyList(),
    val headSize: List<SpecialMeasurement> = emptyList(),
    val bmi: List<SpecialMeasurement> = emptyList(),
)

data class ClinicHistory(
    val date: String,
    val title: String = "",
    val comment: String = "",
)

data class SpecialMeasurement(
    val date: String,
    val value: Double
)

enum class BirthSex {
    MALE,
    FEMALE
}