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
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteStudyDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): StudyDataSource {

    override suspend fun getStudies(patientId: String): Result<List<Study>, BambiniError> {
        val response: Result<List<StudyDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies",
            )
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toStudy() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateStudy(patientId: String, study: Study): Result<Study, BambiniError> {
        val response: Result<StudyDto, BambiniError> = safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies"
            ) {
                setBody(study.toStudyDto())
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.toStudy())
            is Result.Error<*> -> response
        }
    }

    override suspend fun deleteStudy(patientId: String, study: Study): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/studies"
            ) {
                setBody(study.toStudyDto())
            }
        }
    }
}
