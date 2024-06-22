package com.example.chatappclient.di

import com.example.chatappclient.common.remote.SessionPrefs
import com.example.chatappclient.common.remote.SessionPrefsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Cache module
 * This DI module will be responsible of providing caching dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Cache module
 */
val cacheModule = module {
    single <SessionPrefs>{ SessionPrefsImpl(androidContext()) }
}