package com.ascarafia.bambinicore.data.repositories

import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.datasource.PatientDataSource
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.repositories.PatientListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class PatientListRepositoryImpl(
    private val remoteDataSource: PatientDataSource,
    private val accountDataSource: AccountDataSource,
    private val repositoryDispatcher: CoroutineDispatcher = Dispatchers.IO
): PatientListRepository {

    private val patientList = MutableStateFlow<List<Patient>>(emptyList())

    override suspend fun sync() {
        withContext(repositoryDispatcher) {
            val remotePatientList = remoteDataSource.getPatients(
                accountId = accountDataSource.getAccountId().orEmpty()
            )
            if (remotePatientList is Result.Success) {
                patientList.value = remotePatientList.data
            }
        }
    }

    override fun getPatients(): Flow<List<Patient>> {
        return patientList.asStateFlow()
    }

    override suspend fun updatePatientInfo(patient: Patient): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
                remoteDataSource.updatePatient(
                    accountId = accountDataSource.getAccountId().orEmpty(),
                    patient = patient
                )
            }

    }

    override suspend fun deletePatient(patient: Patient): EmptyResult<BambiniError> {
        val deletePatient = patient.copy(isDeleted = true)
        return updatePatientInfo(deletePatient)
    }

    override suspend fun getPatient(patientId: String): Patient? {
        return withContext(repositoryDispatcher) {
            val result = remoteDataSource.getPatient(
                patientId = patientId,
                accountId = accountDataSource.getAccountId().orEmpty()
            )
            if (result is Result.Success) result.data else null
        }
    }
}
