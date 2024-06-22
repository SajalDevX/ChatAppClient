package com.example.chatappclient.di

import com.example.chatappclient.domain.usecase.auth.LoginUseCase
import com.example.chatappclient.domain.usecase.auth.SignupUseCase
import org.koin.dsl.module

/**
 * Repositories module
 * This DI module will be responsible of providing use case dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Repositories module
 */
val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { SignupUseCase(get()) }
//    single { FriendListUseCase(get()) }
//    single { GetRoomHistoryUseCase(get()) }
}