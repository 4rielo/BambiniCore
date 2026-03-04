package com.ascarafia.bambinicore.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PatientDatabaseConstructor : RoomDatabaseConstructor<PatientDatabase> {
    override fun initialize(): PatientDatabase
}