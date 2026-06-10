package com.ascarafia.bambinicore.domain.repositories

import com.ascarafia.bambinicore.domain.model.Allergy
import com.ascarafia.bambinicore.domain.model.Consultation
import com.ascarafia.bambinicore.domain.model.Guardian
import com.ascarafia.bambinicore.domain.model.Measurement
import com.ascarafia.bambinicore.domain.model.Medication
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Study
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface PatientDetailRepository {

    suspend fun getPatientAllergies(patientId: String): Result<List<Allergy>, BambiniError>
    suspend fun upsertPatientAllergy(patientId: String, allergy: Allergy): Result<Unit, BambiniError>
    suspend fun deletePatientAllergy(patientId: String, allergy: Allergy): Result<Unit, BambiniError>

    suspend fun getPatientConsultations(patientId: String): Result<List<Consultation>, BambiniError>
    suspend fun upsertPatientConsultation(patientId: String, consultation: Consultation): Result<Unit, BambiniError>
    suspend fun deletePatientConsultation(patientId: String, consultation: Consultation): Result<Unit, BambiniError>

    suspend fun getPatientGuardians(patientId: String): Result<List<Guardian>, BambiniError>
    suspend fun upsertPatientGuardian(patientId: String, guardian: Guardian): Result<Unit, BambiniError>
    suspend fun deletePatientGuardian(patientId: String, guardian: Guardian): Result<Unit, BambiniError>

    suspend fun getPatientMeasurements(patientId: String): Result<List<Measurement>, BambiniError>
    suspend fun upsertPatientMeasurement(patientId: String, measurement: Measurement): Result<Unit, BambiniError>
    suspend fun deletePatientMeasurement(patientId: String, measurement: Measurement): Result<Unit, BambiniError>

    suspend fun getPatientMedications(patientId: String): Result<List<Medication>, BambiniError>
    suspend fun upsertPatientMedication(patientId: String, medication: Medication): Result<Unit, BambiniError>
    suspend fun deletePatientMedication(patientId: String, medication: Medication): Result<Unit, BambiniError>

    suspend fun getPatientStudies(patientId: String): Result<List<Study>, BambiniError>
    suspend fun upsertPatientStudy(patientId: String, study: Study): Result<Unit, BambiniError>
    suspend fun deletePatientStudy(patientId: String, study: Study): Result<Unit, BambiniError>

}