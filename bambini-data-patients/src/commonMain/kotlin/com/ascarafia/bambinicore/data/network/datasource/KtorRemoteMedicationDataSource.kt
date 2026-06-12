package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.MedicationDto
import com.ascarafia.bambinicore.data.mappers.toMedication
import com.ascarafia.bambinicore.data.mappers.toMedicationDto
import com.ascarafia.bambinicore.domain.datasource.MedicationDataSource
import com.ascarafia.bambinicore.domain.model.Medication
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteMedicationDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): MedicationDataSource {

    override suspend fun getMedications(patientId: String): Result<List<Medication>, BambiniError> {
        val response: Result<List<MedicationDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/medications",
            )
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toMedication() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateMedication(patientId: String, medication: Medication): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/medications/${medication.id}"
            ) {
                setBody(medication.toMedicationDto(
                    tenantId = ""
                ))
            }
        }
    }

    override suspend fun deleteMedication(patientId: String, medicationId: String): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/medications/$medicationId"
            )
        }
    }
}
