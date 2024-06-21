package com.example.chatappclient.domain.model.user

data class User(
    val username: String?,
    val email: String?,
    val token: String?,
    val avatar: String?,
    val isLoggedIn: Boolean? = false
)