package com.ascarafia.bambinicore.data.settings_manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect fun createDataStore(producePath: () -> String): DataStore<Preferences>

internal const val DATA_STORE_FILE_NAME = "app.preferences_pb"