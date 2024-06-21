package com.example.chatappclient.data.remote.dto.auth.signup.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupRequestDto(
    @SerialName("email")
    val email: String?,
    @SerialName("password")
    val password: String?,
    @SerialName("username")
    val username: String?
)