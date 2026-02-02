package com.ascarafia.bambinicore.data.datasource

import com.ascarafia.bambinicore.data.settings_manager.SettingsManager
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource


class AccountLocalDataSource(
    private val settingsManager: SettingsManager
): AccountDataSource {
    override fun saveToken(token: String) {
        settingsManager.putString(LocalStorageKeys.TOKEN.name, token)
    }

    override fun getToken(): String? {
        return settingsManager.getString(LocalStorageKeys.TOKEN.name)
    }

    override fun saveRefreshToken(refreshToken: String) {
        settingsManager.putString(LocalStorageKeys.REFRESH_TOKEN.name, refreshToken)
    }

    override fun getRefreshToken(): String? {
        return settingsManager.getString(LocalStorageKeys.REFRESH_TOKEN.name)
    }
    override fun saveLastUpdate(lastUpdate: String) {
        settingsManager.putString(LocalStorageKeys.LAST_UPDATE.name, lastUpdate)
    }

    override fun getLastUpdate(): String? {
        return settingsManager.getString(LocalStorageKeys.LAST_UPDATE.name)
    }

    override fun getAccountId(): String? {
        return settingsManager.getString(LocalStorageKeys.ACCOUNT_ID.name)
    }

    override fun saveAccountId(accountId: String) {
        settingsManager.putString(LocalStorageKeys.ACCOUNT_ID.name, accountId)
    }
}

enum class LocalStorageKeys {
    TOKEN,
    REFRESH_TOKEN,
    ACCOUNT_ID,
    LAST_UPDATE
}