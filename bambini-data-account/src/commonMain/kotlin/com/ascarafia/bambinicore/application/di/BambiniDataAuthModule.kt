package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambini.data.repositories.AccountRepositoryImpl
import com.ascarafia.bambini.data.repositories.SessionRepositoryImpl
import com.ascarafia.bambini.domain.repositories.AccountRepository
import com.ascarafia.bambini.domain.repositories.SessionRepository
import com.ascarafia.bambinicore.data.network.datasource.KtorAuthDataSource
import com.ascarafia.bambinicore.domain.datasource.AuthDataSource
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bambiniDataAuthModule: List<Module> get() = listOf<Module>(
    authRepositoryModule, authDataSourceModule
) + bambiniNetworkModule

val authRepositoryModule: Module = module {
    singleOf(::SessionRepositoryImpl) bind SessionRepository::class
    singleOf(::AccountRepositoryImpl) bind AccountRepository::class
}

val authDataSourceModule: Module = module {
    singleOf(::KtorAuthDataSource) bind AuthDataSource::class
}