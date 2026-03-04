package com.ascarafia.bambini.data.database.type_converter

import androidx.room.TypeConverter
import com.ascarafia.bambinicore.data.database.SpecialMeasurementEntity
import kotlinx.serialization.json.Json

object SpecialMeasurementListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<SpecialMeasurementEntity> {
        return Json.Default.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(value: List<SpecialMeasurementEntity>): String {
        return Json.Default.encodeToString(value)
    }
}