package com.example.chatappclient.di

import com.example.chatappclient.presentation.auth.login.LoginViewModel
import com.example.chatappclient.presentation.auth.signup.SignupViewModel
import com.example.chatappclient.presentation.chat_room.ChatRoomViewModel
import com.example.chatappclient.presentation.friend_list.FriendListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignupViewModel(get(), get()) }
    viewModel { FriendListViewModel(get(), get()) }
    viewModel { ChatRoomViewModel(get(), get(), get()) }
}