package org.big3.clyq.di

import org.big3.clyq.interfaces.AuthRepository
import org.big3.clyq.repository.AuthRepositoryImpl
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
}