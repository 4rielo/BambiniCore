package com.ascarafia.bambini.data.database.type_converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object StringListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.Default.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(value: List<String>): String {
        return Json.Default.encodeToString(value)
    }
}