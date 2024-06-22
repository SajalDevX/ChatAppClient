package com.example.chatappclient.di

import com.example.chatappclient.data.repository.AuthRepositoryImpl
import com.example.chatappclient.data.repository.ChatRepositoryImpl
import com.example.chatappclient.domain.repository.AuthRepository
import com.example.chatappclient.domain.repository.ChatRepository
import org.koin.dsl.module

/**
 * Repositories module
 * This DI module will be responsible of providing repositories dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Repositories module
 */
val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ChatRepository> { ChatRepositoryImpl(get(), get()) }
}