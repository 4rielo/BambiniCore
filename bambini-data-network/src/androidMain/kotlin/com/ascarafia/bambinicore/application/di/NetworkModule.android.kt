package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.settings_manager.SettingsManager
import com.ascarafia.bambinicore.domain.AndroidLanguageProvider
import com.ascarafia.bambinicore.domain.LanguageProvider
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val networkPlatformModule: Module = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { SettingsManager(androidApplication()) }
    singleOf(::AndroidLanguageProvider) bind LanguageProvider::class
}
