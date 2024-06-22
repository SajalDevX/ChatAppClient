package com.example.chatappclient

import android.app.Application
import com.example.chatappclient.di.appModule
import com.example.chatappclient.di.cacheModule
import com.example.chatappclient.di.networkModule
import com.example.chatappclient.di.repositoryModule
import com.example.chatappclient.di.useCaseModule
import com.example.chatappclient.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ChatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ChatApplication)
            modules(
                appModule,
                cacheModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}