package com.ascarafia.bambinicore.domain.repositories

import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import kotlinx.coroutines.flow.Flow

interface PatientListRepository {

    suspend fun sync()
    fun getPatients(): Flow<List<Patient>>
    suspend fun updatePatientInfo(patient: Patient): Result<Unit, BambiniError>
    suspend fun deletePatient(patient: Patient): EmptyResult<BambiniError>
    suspend fun getPatient(patientId: String): Patient?
}