package com.ascarafia.bambinicore.data.settings_manager

expect class SettingsManager {
    fun putString(key: String, value: String)

    fun getString(key: String): String?

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    fun clear()
}
