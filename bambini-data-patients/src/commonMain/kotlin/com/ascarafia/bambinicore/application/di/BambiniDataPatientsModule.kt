package com.ascarafia.bambinicore.application.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ascarafia.bambinicore.data.database.DatabaseFactory
import com.ascarafia.bambinicore.data.database.PatientDatabase
import com.ascarafia.bambinicore.data.database.migrations.DatabaseMigrations.MIGRATION_1_2
import com.ascarafia.bambinicore.data.database.migrations.DatabaseMigrations.MIGRATION_2_3
import com.ascarafia.bambinicore.data.datasources.LocalPatientsDataSourceImpl
import com.ascarafia.bambinicore.data.network.datasource.KtorRemotePatientDataSource
import com.ascarafia.bambinicore.data.repositories.PatientRepositoryImpl
import com.ascarafia.bambinicore.data.settings_manager.SettingsManager
import com.ascarafia.bambinicore.domain.datasource.LocalPatientsDataSource
import com.ascarafia.bambinicore.domain.datasource.PatientDataSource
import com.ascarafia.bambinicore.domain.repositories.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val bambiniDataPatientsModule: Module = module {
    platformModule + repositoryModule + databaseModule + dataSourceModule + bambiniNetworkModule
}

expect val platformModule: Module

val repositoryModule: Module = module {
    singleOf(::PatientRepositoryImpl) bind PatientRepository::class
}

val databaseModule: Module = module {
    single {
        get<DatabaseFactory>().create()
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single { get<PatientDatabase>().patientDao }
}

val dataSourceModule: Module = module {
    singleOf(::KtorRemotePatientDataSource) bind PatientDataSource::class
    singleOf(::LocalPatientsDataSourceImpl) bind LocalPatientsDataSource::class
}