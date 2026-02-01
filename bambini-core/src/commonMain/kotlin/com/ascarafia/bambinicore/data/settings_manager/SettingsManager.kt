package com.ascarafia.bambini.data.settings_manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map

class SettingsManager(private val dataStore: DataStore<Preferences>) {
    suspend fun putString(key: String, value: String) {
        dataStore.updateData {
            it.toMutablePreferences().apply {
                this[stringPreferencesKey(key)] = value
            }
        }
    }

    suspend fun getString(key: String): String? {
        return dataStore.data.lastOrNull()?.get(stringPreferencesKey(key))
    }

    suspend fun putBoolean(key: String, value: Boolean) {
        dataStore.updateData {
            it.toMutablePreferences().apply {
                this[stringPreferencesKey(key)] = value.toString()
            }
        }
    }

    suspend fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return dataStore.data.map {
            it[stringPreferencesKey(key)] ?: defaultValue.toString()
        }.lastOrNull().toBoolean()
    }

    suspend fun clear() {
        dataStore.updateData {
            it.toMutablePreferences().apply {
                clear()
            }
        }
    }
}
