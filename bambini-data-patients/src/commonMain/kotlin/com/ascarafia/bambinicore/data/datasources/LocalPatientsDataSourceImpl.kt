package com.ascarafia.bambinicore.data.datasources

import com.ascarafia.bambinicore.data.database.PatientDao
import com.ascarafia.bambinicore.data.database.mappers.toPatient
import com.ascarafia.bambinicore.data.database.mappers.toPatientEntity
import com.ascarafia.bambinicore.domain.datasource.LocalPatientsDataSource
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.error.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPatientsDataSourceImpl(
    val patientsDao: PatientDao
): LocalPatientsDataSource {
    override suspend fun upsertPatient(patient: Patient): EmptyResult<DataError> {
        return try {
            patientsDao.upsert(patient.toPatientEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun getPatient(patientId: String): Patient? {
        return patientsDao.getPatient(patientId)?.toPatient()
    }

    override suspend fun getPatientList(): List<Patient> {
        return patientsDao.getPatientList().map { it.toPatient() }
    }

    override fun getFlowPatientList(): Flow<List<Patient>> {
        return patientsDao.getFlowPatientList().map { it.map { it.toPatient() } }
    }

    override suspend fun getPatientByDocumentNumber(id: String): Patient? {
        return patientsDao.getPatientByDocumentNumber(id)?.toPatient()
    }
}