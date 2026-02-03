package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.HttpClientFactory
import com.ascarafia.bambinicore.data.datasource.AccountLocalDataSource
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bambiniNetworkModule = module {
    networkModule + platformModule + accountModule
}

expect val platformModule: Module

val accountModule = module {
    singleOf(::AccountLocalDataSource) bind AccountDataSource::class
}
val networkModule = module {
    single { HttpClientFactory.create(get(), get() ) } bind HttpClient::class
}