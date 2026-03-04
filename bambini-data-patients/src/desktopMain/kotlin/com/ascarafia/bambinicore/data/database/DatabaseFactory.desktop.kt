package com.ascarafia.bambinicore.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<PatientDatabase> {
        val os = System.getProperty("os.name").lowercase()

        val userHome = System.getProperty("user.home")

        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Bambini")
            os.contains("mac") -> File(userHome, "Library/Application Support/Bambini")
            else -> File(userHome, ".local/share/Bambini")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, PatientDatabase.DATABASE_NAME)

        return Room.databaseBuilder(dbFile.absolutePath)
    }
}