package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.network.HttpClientFactory
import com.ascarafia.bambinicore.data.datasource.AccountLocalDataSource
import com.ascarafia.bambinicore.data.network.LocalTokenProvider
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.network.TokenProvider
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bambiniNetworkModule: List<Module> get() = listOf(
    bambiniNetworkHttpClientModule, networkPlatformModule, bambiniNetworkAccountDatasourceModule
)

expect val networkPlatformModule: Module

val bambiniNetworkAccountDatasourceModule = module {
    singleOf(::AccountLocalDataSource) bind AccountDataSource::class
    singleOf(::LocalTokenProvider) bind TokenProvider::class
}
val bambiniNetworkHttpClientModule = module {
    single { HttpClientFactory.create(get(), get(), get() ) } bind HttpClient::class
}