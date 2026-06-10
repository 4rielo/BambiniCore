package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.ConsultationDto
import com.ascarafia.bambinicore.data.mappers.toConsultation
import com.ascarafia.bambinicore.data.mappers.toConsultationDto
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.datasource.ConsultationDataSource
import com.ascarafia.bambinicore.domain.model.Consultation
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteConsultationDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
    private val accountDatasource: AccountDataSource
): ConsultationDataSource {

    override suspend fun getConsultations(patientId: String): Result<List<Consultation>, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        val response: Result<List<ConsultationDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/consultations",
            ) {
                parameter("tenantId", accountId)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toConsultation() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateConsultation(patientId: String, consultation: Consultation): Result<Unit, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/consultations/${consultation.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(consultation.toConsultationDto(
                    tenantId = accountId.orEmpty(),
                    patientId = patientId,
                    doctorId = "", // Needs to be handled by app logic
                    createdAt = consultation.date,
                    updatedAt = consultation.date
                ))
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }

    override suspend fun deleteConsultation(patientId: String, consultationId: String): Result<Unit, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/consultations/$consultationId"
            ) {
                parameter("tenantId", accountId)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }
}
