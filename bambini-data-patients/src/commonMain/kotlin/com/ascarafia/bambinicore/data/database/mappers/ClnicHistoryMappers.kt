package com.ascarafia.bambinicore.data.database.mappers

import com.ascarafia.bambinicore.data.database.ClinicHistoryEntity
import com.ascarafia.bambinicore.domain.model.ClinicHistory

fun ClinicHistoryEntity.toClinicHistory(): ClinicHistory {
    return ClinicHistory(
        date = date,
        title = title,
        comment = comment
    )
}

fun ClinicHistory.toClinicHistoryEntity(): ClinicHistoryEntity {
    return ClinicHistoryEntity(
        date = date,
        title = title,
        comment = comment
    )
}