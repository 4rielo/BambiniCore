package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface PatientDataSource {

    suspend fun getPatients(
        lastUpdated: String? = null
    ): Result<List<Patient>, BambiniError>

    suspend fun getPatient(
        patientId: String
    ): Result<Patient, BambiniError>

    suspend fun updatePatient(
        patient: Patient
    ): Result<Patient, BambiniError>
}