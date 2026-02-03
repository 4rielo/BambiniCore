package com.ascarafia.bambinicore.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class PatientEntity(
    @PrimaryKey(autoGenerate = false) val patientId: String,
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
    val clinicHistory: List<ClinicHistoryEntity> = emptyList(),
    val otherStudies: List<ClinicHistoryEntity> = emptyList(),
    val size: List<SpecialMeasurementEntity> = emptyList(),
    val weight: List<SpecialMeasurementEntity> = emptyList(),
    val headSize: List<SpecialMeasurementEntity> = emptyList(),
    val bmi: List<SpecialMeasurementEntity> = emptyList(),
)

@Serializable
data class ClinicHistoryEntity(
    val date: String,
    val title: String = "",
    val comment: String = "",
)

@Serializable
data class SpecialMeasurementEntity(
    val date: String,
    val value: Double
)
