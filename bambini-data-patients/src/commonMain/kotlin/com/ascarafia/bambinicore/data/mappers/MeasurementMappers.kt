package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.MeasurementDto
import com.ascarafia.bambinicore.domain.model.Measurement
import com.ascarafia.bambinicore.domain.model.MeasurementType
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils

fun MeasurementDto.toMeasurement(): Measurement {
    return Measurement(
        id = id.orEmpty(),
        patientId = patientId.orEmpty(),
        doctorId = doctorId.orEmpty(),
        type = try { 
            MeasurementType.valueOf(type.orEmpty()) 
        } catch (e: Exception) { 
            MeasurementType.WEIGHT 
        },
        value = value ?: 0.0,
        unit = unit.orEmpty(),
        measuredAt = measuredAt ?: DateTimeUtils.getCurrentInstant()
    )
}

fun Measurement.toMeasurementDto(): MeasurementDto {
    return MeasurementDto(
        id = id,
        patientId = patientId,
        doctorId = doctorId,
        type = type.name,
        value = value,
        unit = unit,
        measuredAt = measuredAt,
    )
}
