package org.big3.clyq

import android.app.Application
import org.big3.clyq.di.authModule
import org.big3.clyq.di.dataMapperModule
import org.big3.clyq.di.networkModule
import org.big3.clyq.di.preferenceModule
import org.big3.clyq.di.remoteDataModule
import org.big3.clyq.di.tokenModule
import org.big3.clyq.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ClyqApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ClyqApplication)
            modules(
                listOf(
                    preferenceModule,
                    tokenModule,
                    networkModule,
                    dataMapperModule,
                    remoteDataModule,
                    authModule,
                    viewModelModule
                )
            )
        }

    }

}