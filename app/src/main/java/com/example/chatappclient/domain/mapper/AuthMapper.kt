package com.example.chatappclient.domain.mapper

import com.example.chatappclient.data.remote.dto.auth.login.response.LoginResponseDto
import com.example.chatappclient.data.remote.dto.auth.signup.response.SignupResponseDto
import com.example.chatappclient.domain.model.auth.LoginResponse
import com.example.chatappclient.domain.model.auth.SignupResponse
import com.example.chatappclient.domain.model.user.User

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    token = data?.token,
    username = data?.user?.username,
    email = data?.user?.email,
    avatar = data?.user?.avatar,
    errorMessage = error?.message
)

fun SignupResponseDto.toSignupResponse() = SignupResponse(
    token = data?.token,
    username = data?.user?.username,
    email = data?.user?.email,
    avatar = data?.user?.avatar,
    errorMessage = error?.message
)

fun LoginResponse.toUser() = User(
    username = username,
    email = email,
    token = token,
    avatar = avatar
)

fun SignupResponse.toUser() = User(
    username = username,
    email = email,
    token = token,
    avatar = avatar
)