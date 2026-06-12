package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.PatientDto
import com.ascarafia.bambinicore.data.mappers.toPatient
import com.ascarafia.bambinicore.data.mappers.toPatientDto
import com.ascarafia.bambinicore.domain.datasource.PatientDataSource
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemotePatientDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): PatientDataSource {

    override suspend fun getPatients(accountId: String): Result<List<Patient>, BambiniError> {
        val response: Result<List<PatientDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients",
            ) {
                parameter("tenantId", accountId)
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toPatient() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun getPatient(patientId: String, accountId: String): Result<Patient, BambiniError> {
        val response: Result<PatientDto, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId",
            ) {
                parameter("tenantId", accountId)
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.toPatient())
            is Result.Error<*> -> response
        }
    }

    override suspend fun updatePatient(accountId: String, patient: Patient): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/${patient.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(patient.toPatientDto(
                    tenantId = accountId,
                ))
            }
        }
    }

    override suspend fun deletePatient(patientId: String, accountId: String): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId"
            ) {
                parameter("tenantId", accountId)
            }
        }
    }
}
