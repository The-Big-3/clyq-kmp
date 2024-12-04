package org.big3.clyq.di

import com.russhwolf.settings.Settings
import org.big3.clyq.core.models.DomainDataMapper
import org.big3.clyq.core.models.DomainDataMapperImpl
import org.big3.clyq.core.network.ApiService
import org.big3.clyq.core.network.ApiServiceImpl
import org.big3.clyq.core.network.NetworkClient
import org.big3.clyq.interfaces.RemoteDataRepository
import org.big3.clyq.interfaces.TokenPreferences
import org.big3.clyq.repository.RemoteDataRepositoryImpl
import org.koin.dsl.module


val networkModule = module{
    single { NetworkClient().client}
    single<ApiService>{ApiServiceImpl(get(),get())}
}


val dataMapperModule = module {
    single<DomainDataMapper> { DomainDataMapperImpl() }
}

val remoteDataModule = module {
    single<RemoteDataRepository> { RemoteDataRepositoryImpl(get(), get()) }
}