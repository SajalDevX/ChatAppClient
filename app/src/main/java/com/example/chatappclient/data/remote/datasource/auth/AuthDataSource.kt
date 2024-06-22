package com.example.chatappclient.data.remote.datasource.auth

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.auth.signup.request.SignupRequestDto
import com.example.chatappclient.data.remote.dto.auth.signup.response.SignupResponseDto
import com.example.chatappclient.data.remote.dto.auth.login.request.LoginRequestDto
import com.example.chatappclient.data.remote.dto.auth.login.response.LoginResponseDto

interface AuthDataSource {
    suspend fun login(request: LoginRequestDto): ResponseResource<LoginResponseDto>
    suspend fun signup(request: SignupRequestDto): ResponseResource<SignupResponseDto>
}