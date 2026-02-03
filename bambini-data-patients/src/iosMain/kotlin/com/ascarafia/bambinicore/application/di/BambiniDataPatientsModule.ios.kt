package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module


actual val platformModule: Module = module {
    single { DatabaseFactory() }
}