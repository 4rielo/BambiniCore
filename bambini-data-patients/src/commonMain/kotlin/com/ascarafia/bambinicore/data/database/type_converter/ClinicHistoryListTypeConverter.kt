package com.ascarafia.bambini.data.database.type_converter

import androidx.room.TypeConverter
import com.ascarafia.bambinicore.data.database.ClinicHistoryEntity
import kotlinx.serialization.json.Json

object ClinicHistoryListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<ClinicHistoryEntity> {
        return Json.Default.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(value: List<ClinicHistoryEntity>): String {
        return Json.Default.encodeToString(value)
    }
}