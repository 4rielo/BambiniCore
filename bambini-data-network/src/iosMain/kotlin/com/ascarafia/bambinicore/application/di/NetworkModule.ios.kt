package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.settings_manager.SettingsManager
import com.ascarafia.bambinicore.domain.IosLanguageProvider
import com.ascarafia.bambinicore.domain.LanguageProvider
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val networkPlatformModule: Module = module {
    single<HttpClientEngine> { Darwin.create() }
    single { SettingsManager() }
    singleOf(::IosLanguageProvider) bind LanguageProvider::class
}
