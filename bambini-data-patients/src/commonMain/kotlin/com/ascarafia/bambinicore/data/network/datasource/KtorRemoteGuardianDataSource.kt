package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.GuardianDto
import com.ascarafia.bambinicore.data.mappers.toGuardian
import com.ascarafia.bambinicore.data.mappers.toGuardianDto
import com.ascarafia.bambinicore.domain.datasource.GuardianDataSource
import com.ascarafia.bambinicore.domain.model.Guardian
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteGuardianDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): GuardianDataSource {

    override suspend fun getGuardians(patientId: String, accountId: String): Result<List<Guardian>, BambiniError> {
        val response: Result<List<GuardianDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/guardians",
            ) {
                parameter("tenantId", accountId)
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toGuardian() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateGuardian(patientId: String, accountId: String, guardian: Guardian): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/guardians/${guardian.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(guardian.toGuardianDto(
                    tenantId = accountId
                ))
            }
        }
    }

    override suspend fun deleteGuardian(patientId: String, accountId: String, guardianId: String): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/guardians/$guardianId"
            ) {
                parameter("tenantId", accountId)
            }
        }
    }
}
