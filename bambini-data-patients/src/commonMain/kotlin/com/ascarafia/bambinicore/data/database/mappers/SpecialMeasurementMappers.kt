package com.ascarafia.bambinicore.data.database.mappers

import com.ascarafia.bambinicore.data.database.SpecialMeasurementEntity
import com.ascarafia.bambinicore.domain.model.SpecialMeasurement

fun SpecialMeasurement.toSpecialMeasurementEntity(): SpecialMeasurementEntity {
    return SpecialMeasurementEntity(
        date = date,
        value = value
    )
}

fun SpecialMeasurementEntity.toSpecialMeasurement(): SpecialMeasurement {
    return SpecialMeasurement(
        date = date,
        value = value
    )
}