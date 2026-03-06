package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.PatientDto
import com.ascarafia.bambinicore.data.mappers.toPatient
import com.ascarafia.bambinicore.data.mappers.toPatientDto
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.datasource.PatientDataSource
import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemotePatientDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
    private val accountDatasource: AccountDataSource
): PatientDataSource {

    override suspend fun getPatients(
        lastUpdated: String?
    ): Result<List<Patient>, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        val response: Result<List<PatientDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/clients/$accountId/patients",
            ) {
                parameter("lastUpdate", lastUpdated)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toPatient() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun getPatient(patientId: String): Result<Patient, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        val response: Result<PatientDto, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/clients/$accountId/patients/$patientId",
            ) {
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.toPatient())
            is Result.Error<*> -> response
        }
    }

    override suspend fun updatePatient(patient: Patient): Result<Patient, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/clients/$accountId/patients/${patient.patientId}"
            ) {
                setBody(patient.toPatientDto())
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }
}