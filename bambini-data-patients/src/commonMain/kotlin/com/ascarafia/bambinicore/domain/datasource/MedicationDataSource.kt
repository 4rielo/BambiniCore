package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Medication
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface MedicationDataSource {
    suspend fun getMedications(patientId: String, accountId: String): Result<List<Medication>, BambiniError>
    suspend fun updateMedication(patientId: String, accountId: String, medication: Medication): Result<Unit, BambiniError>
    suspend fun deleteMedication(patientId: String, accountId: String, medicationId: String): Result<Unit, BambiniError>
}
