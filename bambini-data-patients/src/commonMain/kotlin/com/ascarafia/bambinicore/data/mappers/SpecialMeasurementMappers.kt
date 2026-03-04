package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.domain.model.SpecialMeasurement
import com.ascarafia.bambinicore.data.network.dto.SpecialMeasurementDto

fun SpecialMeasurement.toSpecialMeasurementDto(): SpecialMeasurementDto {
    return SpecialMeasurementDto(
        date = date,
        value = value
    )
}

fun SpecialMeasurementDto.toSpecialMeasurement(): SpecialMeasurement {
    return SpecialMeasurement(
        date = date,
        value = value
    )
}