package com.ascarafia.bambinicore.data.settings_manager

import java.util.prefs.Preferences

actual class SettingsManager {

    private val prefs: Preferences = Preferences.userRoot().node("app_prefs")

    actual fun putString(key: String, value: String) {
        prefs.put(key, value)
    }

    actual fun getString(key: String): String? {
        return prefs.get(key, null)
    }

    actual fun putBoolean(key: String, value: Boolean) {
        prefs.putBoolean(key, value)
    }

    actual fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    actual fun clear() {
        prefs.clear()
    }
}