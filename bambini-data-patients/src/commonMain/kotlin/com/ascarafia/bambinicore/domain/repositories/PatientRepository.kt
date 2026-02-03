package com.ascarafia.bambinicore.domain.repositories

import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.error.DataError
import com.ascarafia.bambinicore.domain.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepository {

    suspend fun sync()
    fun getPatients(): Flow<List<Patient>>
    suspend fun updatePatientInfo(patient: Patient): EmptyResult<DataError>
    suspend fun deletePatient(patient: Patient): EmptyResult<DataError>
    suspend fun getPatient(patientId: String): Patient?
}