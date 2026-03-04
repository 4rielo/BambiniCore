package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.error.DataError
import kotlinx.coroutines.flow.Flow

interface LocalPatientsDataSource {

    suspend fun upsertPatient(patient: Patient): EmptyResult<DataError>

    suspend fun getPatient(patientId: String): Patient?

    suspend fun getPatientList(): List<Patient>

    fun getFlowPatientList(): Flow<List<Patient>>

    suspend fun getPatientByDocumentNumber(id: String): Patient?
}