package com.ascarafia.bambinicore.data.repositories

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.error.DataError
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import com.ascarafia.bambinicore.domain.use_cases.ListSortingUseCase
import androidx.sqlite.SQLiteException
import com.ascarafia.bambinicore.domain.datasource.LocalPatientsDataSource
import com.ascarafia.bambinicore.domain.datasource.PatientDataSource
import com.ascarafia.bambinicore.domain.repositories.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class PatientRepositoryImpl(
    private val localDataSource: LocalPatientsDataSource,
    private val remoteDataSource: PatientDataSource,
): PatientRepository {

    override suspend fun sync() {
        withContext(Dispatchers.IO) {
            val localPatients = localDataSource.getPatientList()
            val remotePatientsResponse = remoteDataSource.getPatients()
            if (remotePatientsResponse is Result.Success) {
                val remotePatients = remotePatientsResponse.data

                val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase.getTasksToUpdate(
                    localPatients,
                    remotePatients
                )

                for (remotePatient in remoteListToUpdate) {
                    remoteDataSource.updatePatient(remotePatient)
                }

                for (localPatient in localListToUpdate) {
                    localDataSource.upsertPatient(localPatient)
                }
            }
        }
    }

    override fun getPatients(): Flow<List<Patient>> {
        return localDataSource.getFlowPatientList().onEach {
            it.filter { patient -> !patient.isDeleted }
        }
    }

    override suspend fun updatePatientInfo(patient: Patient): EmptyResult<DataError> {
        return try {
            val lastUpdate = DateTimeUtils.getCurrentDateTimeString()
            val updatedPatient = patient.copy(lastUpdated = lastUpdate)
            localDataSource.upsertPatient(updatedPatient)
        } catch(e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deletePatient(patient: Patient): EmptyResult<DataError> {
        val deletePatient = patient.copy(isDeleted = true)
        return updatePatientInfo(deletePatient)
    }

    override suspend fun getPatient(patientId: String): Patient? {
        return localDataSource.getPatient(patientId = patientId)
    }
}