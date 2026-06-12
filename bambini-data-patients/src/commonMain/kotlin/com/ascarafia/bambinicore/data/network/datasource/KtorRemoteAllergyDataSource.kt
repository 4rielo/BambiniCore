package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.AllergyDto
import com.ascarafia.bambinicore.data.mappers.toAllergy
import com.ascarafia.bambinicore.data.mappers.toAllergyDto
import com.ascarafia.bambinicore.domain.datasource.AllergyDataSource
import com.ascarafia.bambinicore.domain.model.Allergy
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteAllergyDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): AllergyDataSource {

    override suspend fun getAllergies(patientId: String, accountId: String): Result<List<Allergy>, BambiniError> {
        val response: Result<List<AllergyDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/allergies",
            ) {
                parameter("tenantId", accountId)
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toAllergy() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateAllergy(
        patientId: String,
        accountId: String,
        allergy: Allergy
    ): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/allergies/${allergy.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(allergy.toAllergyDto(
                    tenantId = accountId,
                    patientId = patientId
                ))
            }
        }
    }

    override suspend fun deleteAllergy(
        patientId: String,
        accountId: String,
        allergyId: String
    ): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/allergies/$allergyId"
            ) {
                parameter("tenantId", accountId)
            }
        }
    }
}
