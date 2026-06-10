package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.MedicationDto
import com.ascarafia.bambinicore.data.mappers.toMedication
import com.ascarafia.bambinicore.data.mappers.toMedicationDto
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.datasource.MedicationDataSource
import com.ascarafia.bambinicore.domain.model.Medication
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteMedicationDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
    private val accountDatasource: AccountDataSource
): MedicationDataSource {

    override suspend fun getMedications(patientId: String): Result<List<Medication>, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        val response: Result<List<MedicationDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/medications",
            ) {
                parameter("tenantId", accountId)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toMedication() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateMedication(patientId: String, medication: Medication): Result<Unit, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/medications/${medication.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(medication.toMedicationDto(
                    tenantId = accountId.orEmpty()
                ))
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }

    override suspend fun deleteMedication(patientId: String, medicationId: String): Result<Unit, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/medications/$medicationId"
            ) {
                parameter("tenantId", accountId)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }
}
