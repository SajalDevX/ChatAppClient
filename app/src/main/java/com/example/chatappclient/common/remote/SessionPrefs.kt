package com.example.chatappclient.common.remote

import com.example.chatappclient.domain.model.user.User


interface SessionPrefs {
    fun saveUser(user: User)
    fun getUser(): User?
    fun clearSession()
}