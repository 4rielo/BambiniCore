package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.StudyDto
import com.ascarafia.bambinicore.data.mappers.toStudy
import com.ascarafia.bambinicore.data.mappers.toStudyDto
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.datasource.StudyDataSource
import com.ascarafia.bambinicore.domain.model.Study
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteStudyDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
    private val accountDatasource: AccountDataSource
): StudyDataSource {

    override suspend fun getStudies(patientId: String): Result<List<Study>, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        val response: Result<List<StudyDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies",
            ) {
                parameter("tenantId", accountId)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toStudy() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateStudy(patientId: String, study: Study): Result<Unit, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies/${study.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(study.toStudyDto(
                    tenantId = accountId.orEmpty()
                ))
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }

    override suspend fun deleteStudy(patientId: String, studyId: String): Result<Unit, BambiniError> {
        val accountId = accountDatasource.getAccountId()
        val token = accountDatasource.getToken()
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies/$studyId"
            ) {
                parameter("tenantId", accountId)
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
        }
    }
}
