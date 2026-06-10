package com.ascarafia.bambinicore.application.di

import com.ascarafia.bambinicore.data.network.datasource.*
import com.ascarafia.bambinicore.data.repositories.PatientDetailRepositoryImpl
import com.ascarafia.bambinicore.data.repositories.PatientListRepositoryImpl
import com.ascarafia.bambinicore.domain.datasource.*
import com.ascarafia.bambinicore.domain.repositories.PatientDetailRepository
import com.ascarafia.bambinicore.domain.repositories.PatientListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val bambiniDataPatientsModule: List<Module> get() = listOf<Module>(
    patientsRepositoryModule, patientsDataSourceModule
) + bambiniNetworkModule

val patientsRepositoryModule: Module = module {
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
    single {
        PatientListRepositoryImpl(
            get(),
            get(qualifier = named("IODispatcher"))
        )
    } bind PatientListRepository::class

    single {
        PatientDetailRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(qualifier = named("IODispatcher"))
        )
    } bind PatientDetailRepository::class
}

val patientsDataSourceModule: Module = module {
    singleOf(::KtorRemotePatientDataSource) bind PatientDataSource::class
    singleOf(::KtorRemoteAllergyDataSource) bind AllergyDataSource::class
    singleOf(::KtorRemoteConsultationDataSource) bind ConsultationDataSource::class
    singleOf(::KtorRemoteGuardianDataSource) bind GuardianDataSource::class
    singleOf(::KtorRemoteMeasurementDataSource) bind MeasurementDataSource::class
    singleOf(::KtorRemoteMedicationDataSource) bind MedicationDataSource::class
    singleOf(::KtorRemoteStudyDataSource) bind StudyDataSource::class
}
