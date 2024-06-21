package com.example.chatappclient.domain.model.auth

data class LoginResponse(
    val token: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val errorMessage: String? = null
)