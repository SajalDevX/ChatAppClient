package com.example.chatappclient.data.remote.dto.auth.login.request

@kotlinx.serialization.Serializable
data class LoginRequestDto(
    val email: String? = null,
    val password: String? = null
)
