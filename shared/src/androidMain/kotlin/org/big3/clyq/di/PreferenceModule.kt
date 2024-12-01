package org.big3.clyq.di

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.big3.clyq.interfaces.TokenPreferences
import org.big3.clyq.repository.TokenPreferencesImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun provideSettings(context: Context): Settings {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPreferences)
    }


}


@Module
@InstallIn(SingletonComponent::class)
object TokenModule {
    @Provides
    @Singleton
    fun provideTokenPreference(settings: Settings): TokenPreferences {
        return TokenPreferencesImpl(settings)
    }
}