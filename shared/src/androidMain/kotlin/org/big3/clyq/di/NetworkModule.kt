package org.big3.clyq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import org.big3.clyq.core.network.ApiService
import org.big3.clyq.core.network.ApiServiceImpl
import org.big3.clyq.interfaces.TokenPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient():HttpClient{
        return HttpClient(Android){
            install(Logging){
                level = LogLevel.INFO
            }
            install(ContentNegotiation){
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            }
        }
    }


    @Provides
    @Singleton
    fun provideApiService(client: HttpClient,tokenPref: TokenPreferences):ApiService{
        return ApiServiceImpl(client,tokenPref)
    }
}