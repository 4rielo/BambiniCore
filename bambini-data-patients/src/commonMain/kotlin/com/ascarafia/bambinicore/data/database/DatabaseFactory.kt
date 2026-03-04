package com.ascarafia.bambinicore.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<PatientDatabase>
}