package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.ConsultationDto
import com.ascarafia.bambinicore.domain.model.Consultation
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils

fun ConsultationDto.toConsultation(): Consultation {
    return Consultation(
        id = id.orEmpty(),
        date = date ?: DateTimeUtils.getCurrentInstant(),
        patientId = patientId.orEmpty(),
        reason = reason.orEmpty(),
        diagnosis = diagnosis,
        treatment = treatment,
        notes = notes
    )
}

fun Consultation.toConsultationDto(): ConsultationDto {
    return ConsultationDto(
        id = id,
        patientId = patientId,
        date = date,
        reason = reason,
        diagnosis = diagnosis,
        treatment = treatment,
        notes = notes,
    )
}
