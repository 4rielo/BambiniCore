package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val bambiniNetworkModule = module {
    networkModule + platformModule
}

expect val platformModule: Module

val networkModule = module {
    single { HttpClientFactory.create(get(), get() ) } bind HttpClient::class
}