package org.big3.clyq.di

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.big3.clyq.interfaces.TokenPreferences
import org.big3.clyq.repository.TokenPreferencesImpl
import org.koin.dsl.module


val preferenceModule = module {
    single<Settings> {
        val context: Context = get()
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        SharedPreferencesSettings(sharedPreferences)
    }
}

val tokenModule = module {
    single<TokenPreferences> { TokenPreferencesImpl(get()) }
}