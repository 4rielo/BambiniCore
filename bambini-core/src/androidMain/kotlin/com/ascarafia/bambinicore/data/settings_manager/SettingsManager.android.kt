package com.ascarafia.bambinicore.data.settings_manager

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
actual class SettingsManager(private val context: Context) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
    actual fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

    actual fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    actual fun putBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }

    actual fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    actual fun clear() {
        prefs.edit { clear() }
    }
}