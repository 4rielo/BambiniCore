package com.ascarafia.bambinicore.data.settings_manager

import platform.Foundation.NSUserDefaults

actual class SettingsManager {

    private val defaults: NSUserDefaults = NSUserDefaults.standardUserDefaults()

    actual fun putString(key: String, value: String) {
        defaults.setObject(value, forKey = key)
    }

    actual fun getString(key: String): String? {
        return defaults.stringForKey(key)
    }

    actual fun putBoolean(key: String, value: Boolean) {
        defaults.setBool(value, forKey = key)
    }

    actual fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (defaults.objectForKey(key) != null) defaults.boolForKey(key) else defaultValue
    }

    actual fun clear() {
        defaults.dictionaryRepresentation().keys.forEach {
            defaults.removeObjectForKey(it as String)
        }
    }
}