package com.ascarafia.bambinicore.data.datasource

import com.ascarafia.bambini.data.settings_manager.SettingsManager
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource


class AccountLocalDataSource(
    private val settingsManager: SettingsManager
): AccountDataSource {
    override suspend fun saveToken(token: String) {
        settingsManager.putString(LocalStorageKeys.TOKEN.name, token)
    }

    override suspend fun getToken(): String? {
        return settingsManager.getString(LocalStorageKeys.TOKEN.name)
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        settingsManager.putString(LocalStorageKeys.REFRESH_TOKEN.name, refreshToken)
    }

    override suspend fun getRefreshToken(): String? {
        return settingsManager.getString(LocalStorageKeys.REFRESH_TOKEN.name)
    }
    override suspend fun saveLastUpdate(lastUpdate: String) {
        settingsManager.putString(LocalStorageKeys.LAST_UPDATE.name, lastUpdate)
    }

    override suspend fun getLastUpdate(): String? {
        return settingsManager.getString(LocalStorageKeys.LAST_UPDATE.name)
    }

    override suspend fun getAccountId(): String? {
        return settingsManager.getString(LocalStorageKeys.ACCOUNT_ID.name)
    }

    override suspend fun saveAccountId(accountId: String) {
        settingsManager.putString(LocalStorageKeys.ACCOUNT_ID.name, accountId)
    }
}

enum class LocalStorageKeys {
    TOKEN,
    REFRESH_TOKEN,
    ACCOUNT_ID,
    LAST_UPDATE
}