package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Measurement
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface MeasurementDataSource {
    suspend fun getMeasurements(patientId: String, accountId: String): Result<List<Measurement>, BambiniError>
    suspend fun updateMeasurement(patientId: String, accountId: String, measurement: Measurement): Result<Unit, BambiniError>
    suspend fun deleteMeasurement(patientId: String, accountId: String, measurementId: String): Result<Unit, BambiniError>
}
