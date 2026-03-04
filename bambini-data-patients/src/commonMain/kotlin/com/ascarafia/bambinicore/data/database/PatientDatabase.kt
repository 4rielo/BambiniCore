package com.ascarafia.bambinicore.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ascarafia.bambini.data.database.type_converter.ClinicHistoryListTypeConverter
import com.ascarafia.bambini.data.database.type_converter.SpecialMeasurementListTypeConverter
import com.ascarafia.bambini.data.database.type_converter.StringListTypeConverter

@Database(
    entities = [PatientEntity::class],
    version = 3
)
@TypeConverters(
    StringListTypeConverter::class,
    SpecialMeasurementListTypeConverter::class,
    ClinicHistoryListTypeConverter::class
)
@ConstructedBy(
    value = PatientDatabaseConstructor::class
)
abstract class PatientDatabase: RoomDatabase() {

    abstract val patientDao: PatientDao

    companion object {
        const val DATABASE_NAME = "patient_db"
    }
}