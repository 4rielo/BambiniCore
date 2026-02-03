package com.ascarafia.bambinicore.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

object DatabaseMigrations {
    //MIGRATION from v1 to v2, added City, Province and Country to PatientEntity
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(connection: SQLiteConnection) {
            connection.execSQL("ALTER TABLE PatientEntity ADD COLUMN city TEXT NOT NULL DEFAULT 'N/A'")
            connection.execSQL("ALTER TABLE PatientEntity ADD COLUMN province TEXT NOT NULL DEFAULT 'N/A'")
            connection.execSQL("ALTER TABLE PatientEntity ADD COLUMN country TEXT NOT NULL DEFAULT 'N/A'")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(connection: SQLiteConnection) {

            connection.execSQL("DROP TABLE IF EXISTS PatientEntity")

            connection.execSQL("""
                CREATE TABLE PatientEntity (
                    patientId TEXT NOT NULL PRIMARY KEY,
                    name TEXT NOT NULL,
                    city TEXT NOT NULL,
                    province TEXT NOT NULL,
                    country TEXT NOT NULL
                )
            """)
        }
    }
}