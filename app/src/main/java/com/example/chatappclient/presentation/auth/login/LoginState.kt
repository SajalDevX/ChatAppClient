package com.example.chatappclient.presentation.auth.login

import com.example.chatappclient.domain.model.auth.LoginResponse

data class LoginState(
    val isLoading: Boolean = false,
    val data: LoginResponse? = null,
    val error: String = ""
)
