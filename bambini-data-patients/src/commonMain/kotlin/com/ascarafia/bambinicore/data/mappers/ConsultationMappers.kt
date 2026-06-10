package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.ConsultationDto
import com.ascarafia.bambinicore.domain.model.Consultation
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import kotlin.time.Instant

fun ConsultationDto.toConsultation(): Consultation {
    return Consultation(
        id = id.orEmpty(),
        date = date?.let { DateTimeUtils.fromIsoString(it) } ?: Instant.DISTANT_PAST,
        reason = reason.orEmpty(),
        diagnosis = diagnosis,
        treatment = treatment,
        notes = notes
    )
}

fun Consultation.toConsultationDto(
    tenantId: String,
    patientId: String,
    doctorId: String,
    createdAt: Instant,
    updatedAt: Instant
): ConsultationDto {
    return ConsultationDto(
        id = id,
        tenantId = tenantId,
        patientId = patientId,
        doctorId = doctorId,
        date = date.toString(),
        reason = reason,
        diagnosis = diagnosis,
        treatment = treatment,
        notes = notes,
        createdAt = createdAt.toString(),
        updatedAt = updatedAt.toString()
    )
}
