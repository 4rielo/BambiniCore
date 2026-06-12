package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.safeCall
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.data.network.dto.MeasurementDto
import com.ascarafia.bambinicore.data.mappers.toMeasurement
import com.ascarafia.bambinicore.data.mappers.toMeasurementDto
import com.ascarafia.bambinicore.domain.datasource.MeasurementDataSource
import com.ascarafia.bambinicore.domain.model.Measurement
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class KtorRemoteMeasurementDataSource(
    private val httpClient: HttpClient,
    private val config: BambiniRemoteConfig,
): MeasurementDataSource {

    override suspend fun getMeasurements(patientId: String, accountId: String): Result<List<Measurement>, BambiniError> {
        val response: Result<List<MeasurementDto>, BambiniError> = safeCall {
            httpClient.get(
                urlString = "${config.baseUrl}/api/patients/$patientId/measurements",
            ) {
                parameter("tenantId", accountId)
            }
        }

        return when(response) {
            is Result.Success -> Result.Success(response.data.map { it.toMeasurement() })
            is Result.Error<*> -> response
        }
    }

    override suspend fun updateMeasurement(patientId: String, accountId: String, measurement: Measurement): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.put(
                urlString = "${config.baseUrl}/api/patients/$patientId/measurements/${measurement.id}"
            ) {
                parameter("tenantId", accountId)
                setBody(measurement.toMeasurementDto(
                    tenantId = accountId
                ))
            }
        }
    }

    override suspend fun deleteMeasurement(patientId: String, accountId: String, measurementId: String): Result<Unit, BambiniError> {
        return safeCall {
            httpClient.delete(
                urlString = "${config.baseUrl}/api/patients/$patientId/measurements/$measurementId"
            ) {
                parameter("tenantId", accountId)
            }
        }
    }
}
