package com.example.chatappclient.domain.repository

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.auth.login.response.LoginResponseDto
import com.example.chatappclient.data.remote.dto.auth.signup.request.SignupRequestDto
import com.example.chatappclient.data.remote.dto.auth.signup.response.SignupResponseDto
import kotlinx.coroutines.flow.Flow
import com.example.chatappclient.data.remote.dto.auth.login.request.LoginRequestDto

interface AuthRepository {
    suspend fun signup(requestDto: SignupRequestDto): Flow<ResponseResource<SignupResponseDto>>
    suspend fun login(requestDto: LoginRequestDto):Flow<ResponseResource<LoginResponseDto>>
}