package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.StudyDto
import com.ascarafia.bambinicore.data.mappers.toStudy
import com.ascarafia.bambinicore.data.mappers.toStudyDto
import com.ascarafia.bambinicore.domain.datasource.StudyDataSource
import com.ascarafia.bambinicore.domain.model.Study
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteStudyDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): StudyDataSource {

    override suspend fun getStudies(patientId: String, accountId: String): Result<List<Study>, BambiniError> {
        val response: Result<List<StudyDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies",
            ) {
                parameter("tenantId", accountId)
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toStudy() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateStudy(patientId: String, accountId: String, study: Study): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies/${study.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(study.toStudyDto(
                    tenantId = accountId
                ))
            }
        }
    }

    override suspend fun deleteStudy(patientId: String, accountId: String, studyId: String): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies/$studyId"
            ) {
                parameter("tenantId", accountId)
            }
        }
    }
}
