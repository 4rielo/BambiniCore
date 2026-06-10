package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.StudyDto
import com.ascarafia.bambinicore.domain.model.Study
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import kotlin.time.Instant

fun StudyDto.toStudy(): Study {
    return Study(
        id = id.orEmpty(),
        patientId = patientId.orEmpty(),
        doctorId = doctorId,
        type = type.orEmpty(),
        description = description,
        result = result,
        date = date?.let { DateTimeUtils.fromIsoString(it) } ?: Instant.DISTANT_PAST,
        attachments = attachments ?: emptyList()
    )
}

fun Study.toStudyDto(
    tenantId: String,
    createdAt: Instant? = null
): StudyDto {
    return StudyDto(
        id = id,
        tenantId = tenantId,
        patientId = patientId,
        doctorId = doctorId,
        type = type,
        description = description,
        result = result,
        date = date.toString(),
        attachments = attachments,
        createdAt = createdAt?.toString()
    )
}
