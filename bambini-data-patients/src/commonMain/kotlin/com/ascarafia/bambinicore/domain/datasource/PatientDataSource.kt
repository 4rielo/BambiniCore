package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.DataError
import com.ascarafia.bambinicore.domain.model.Patient

interface PatientDataSource {

    suspend fun getPatients(
        lastUpdated: String? = null
    ): Result<List<Patient>, DataError.Remote>

    suspend fun getPatient(
        patientId: String
    ): Result<Patient, DataError.Remote>

    suspend fun updatePatient(
        patient: Patient
    ): Result<String, DataError.Remote>
}