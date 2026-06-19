package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.StudyDto
import com.ascarafia.bambinicore.domain.model.Study
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils

fun StudyDto.toStudy(): Study {
    return Study(
        id = id.orEmpty(),
        patientId = patientId.orEmpty(),
        type = type.orEmpty(),
        description = description,
        result = result,
        date = date ?: DateTimeUtils.getCurrentInstant(),
        attachments = attachments ?: emptyList()
    )
}

fun Study.toStudyDto(): StudyDto {
    return StudyDto(
        id = id,
        patientId = patientId,
        type = type,
        description = description,
        result = result,
        date = date,
        attachments = attachments,
    )
}
