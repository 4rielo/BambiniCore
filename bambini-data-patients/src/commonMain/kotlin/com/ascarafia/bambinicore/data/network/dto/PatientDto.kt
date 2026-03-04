package com.ascarafia.bambinicore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PatientDto(
    @SerialName("_id") val patientId: String? = null,
    @SerialName("last_update") val lastUpdated: String? = null,
    @SerialName("is_deleted") val isDeleted: String? = null,
    @SerialName("id_number") val idNumber: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    @SerialName("date_of_birth") val dateOfBirth: String? = null,
    @SerialName("gestation_weeks") val gestationWeeks: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("city") val city: String? = null,
    @SerialName("province") val province: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("social_security") val socialSecurity: String? = null,
    @SerialName("social_security_number") val socialSecurityNumber: String? = null,
    @SerialName("legal_guardian_a") val legalGuardianA: String? = null,
    @SerialName("legal_guardian_a_relationship") val legalGuardianARelationship: String? = null,
    @SerialName("phone_number_a") val phoneNumberA: String? = null,
    @SerialName("legal_guardian_b") val legalGuardianB: String? = null,
    @SerialName("legal_guardian_b_relationship") val legalGuardianBRelationship: String? = null,
    @SerialName("phone_number_b") val phoneNumberB: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("special_comment") val specialComment: String? = null,
    @SerialName("family_history") val familyHistory: String? = null,
    @SerialName("allergies_list") val allergies: List<String>? = emptyList(),
    @SerialName("medication_list") val medications: List<String>? = emptyList(),
    @SerialName("clinic_history") val clinicHistory: List<ClinicHistoryDto>? = emptyList(),
    @SerialName("other_studies") val otherStudies: List<ClinicHistoryDto>? = emptyList(),
    @SerialName("size") val size: List<SpecialMeasurementDto>? = emptyList(),
    @SerialName("weight") val weight: List<SpecialMeasurementDto>? = emptyList(),
    @SerialName("head_size") val headSize: List<SpecialMeasurementDto>? = emptyList(),
    @SerialName("bmi") val bmi: List<SpecialMeasurementDto>? = emptyList(),
)

@Serializable
data class ClinicHistoryDto(
    @SerialName("date") val date: String,
    @SerialName("title") val title: String = "",
    @SerialName("comment") val comment: String = "",
)

@Serializable
data class SpecialMeasurementDto(
    @SerialName("date") val date: String,
    @SerialName("value") val value: Double
)