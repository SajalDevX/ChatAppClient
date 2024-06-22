package com.example.chatappclient.di

import com.example.chatappclient.data.remote.datasource.auth.AuthDataSource
import com.example.chatappclient.data.remote.datasource.auth.AuthDataSourceImpl
import org.koin.dsl.module

/**
 * Network module
 * This DI module will be responsible of providing network dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Network module
 */
val networkModule = module {
    single<AuthDataSource> { AuthDataSourceImpl(get()) }
//    single<ChatRemote> { ChatRemoteImpl(get()) }
}