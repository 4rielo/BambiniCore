package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.dto.ClinicHistoryDto
import com.ascarafia.bambinicore.domain.model.ClinicHistory

fun ClinicHistoryDto.toClinicHistory(): ClinicHistory {
    return ClinicHistory(
        date = date,
        title = title,
        comment = comment
    )
}

fun ClinicHistory.toClinicHistoryDto(): ClinicHistoryDto {
    return ClinicHistoryDto(
        date = date,
        title = title,
        comment = comment
    )
}