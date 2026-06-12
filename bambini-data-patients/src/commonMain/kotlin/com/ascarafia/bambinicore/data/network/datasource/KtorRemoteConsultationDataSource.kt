package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.ConsultationDto
import com.ascarafia.bambinicore.data.mappers.toConsultation
import com.ascarafia.bambinicore.data.mappers.toConsultationDto
import com.ascarafia.bambinicore.domain.datasource.ConsultationDataSource
import com.ascarafia.bambinicore.domain.model.Consultation
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteConsultationDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): ConsultationDataSource {

    override suspend fun getConsultations(patientId: String, accountId: String): Result<List<Consultation>, BambiniError> {
        val response: Result<List<ConsultationDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/consultations",
            ) {
                parameter("tenantId", accountId)
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toConsultation() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateConsultation(patientId: String, accountId: String, consultation: Consultation): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/consultations/${consultation.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(consultation.toConsultationDto(
                    tenantId = accountId,
                    patientId = patientId,
                    doctorId = "", // Needs to be handled by app logic
                    createdAt = consultation.date,
                    updatedAt = consultation.date
                ))
            }
        }
    }

    override suspend fun deleteConsultation(patientId: String, accountId: String, consultationId: String): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/consultations/$consultationId"
            ) {
                parameter("tenantId", accountId)
            }
        }
    }
}
