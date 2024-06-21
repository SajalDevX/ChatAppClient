package com.example.chatappclient.presentation.auth.signup

import com.example.chatappclient.domain.model.auth.SignupResponse

data class SignupState(
    val isLoading: Boolean = false,
    val data: SignupResponse? = null,
    val error: String = ""
)