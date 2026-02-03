package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.settings_manager.SettingsManager
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { SettingsManager() }
}