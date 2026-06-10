package com.ascarafia.bambinicore.domain.model

import kotlin.time.Instant

data class Measurement(
    val id: String,
    val patientId: String,
    val doctorId: String,
    val type: MeasurementType,
    val value: Double,
    val unit: String,
    val measuredAt: Instant,
)

enum class MeasurementType {
    WEIGHT,
    HEIGHT,
    HEAD_CIRCUMFERENCE,
    BMI
}
