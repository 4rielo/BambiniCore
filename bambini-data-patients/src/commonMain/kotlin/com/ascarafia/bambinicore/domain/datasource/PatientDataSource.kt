package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface PatientDataSource {

    suspend fun getPatients(accountId: String): Result<List<Patient>, BambiniError>

    suspend fun getPatient(
        patientId: String,
        accountId: String
    ): Result<Patient, BambiniError>

    suspend fun updatePatient(
        accountId: String,
        patient: Patient
    ): Result<Unit, BambiniError>

    suspend fun deletePatient(
        patientId: String,
        accountId: String
    ): Result<Unit, BambiniError>
}
