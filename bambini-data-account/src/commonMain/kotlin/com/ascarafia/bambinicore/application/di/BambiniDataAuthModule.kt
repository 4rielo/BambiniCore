package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.repositories.AccountRepositoryImpl
import com.ascarafia.bambinicore.data.repositories.SessionRepositoryImpl
import com.ascarafia.bambinicore.domain.repositories.AccountRepository
import com.ascarafia.bambinicore.domain.repositories.SessionRepository
import com.ascarafia.bambinicore.data.network.datasource.KtorAuthDataSource
import com.ascarafia.bambinicore.domain.datasource.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val bambiniAuthModule: List<Module> get() = listOf<Module>(
    authRepositoryModule, authDataSourceModule
) //+ bambiniNetworkModule

val authRepositoryModule: Module = module {
    single(named("AuthIODispatcher")) {
        Dispatchers.IO
    }
    single {
        SessionRepositoryImpl(
            get(),
            get(),
            get(qualifier = named("AuthIODispatcher"))
        )
    } bind SessionRepository::class
    singleOf(::AccountRepositoryImpl) bind AccountRepository::class
}

val authDataSourceModule: Module = module {
    singleOf(::KtorAuthDataSource) bind AuthDataSource::class
}