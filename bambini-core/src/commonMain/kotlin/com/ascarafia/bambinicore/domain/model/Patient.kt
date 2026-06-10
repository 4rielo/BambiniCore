package com.ascarafia.bambinicore.domain.model

import kotlin.time.Instant

data class Patient(
    val id: String,
    val idNumber: String,
    val name: String,
    val lastName: String,
    val dateOfBirth: String,
    val gestationWeeks: Int? = null,
    val gender: String,
    val city: String,
    val province: String,
    val country: String,
    val socialSecurity: String? = null,
    val socialSecurityNumber: String? = null,
    val address: String? = null,
    val email: String? = null,
    val specialComment: String? = null,
    val familyHistory: String? = null,
    val isDeleted: Boolean,
    val updatedAt: Instant,
    val measurements: List<Measurement> = emptyList(),
    val consultations: List<Consultation> = emptyList(),
    val studies: List<Study> = emptyList(),
    val allergies: List<String> = emptyList(),
    val medications: List<Medication> = emptyList(),
    val guardians: List<Guardian> = emptyList(),
)

enum class BirthSex {
    MALE,
    FEMALE
}
