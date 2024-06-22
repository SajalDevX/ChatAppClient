package com.example.chatappclient.data.repository

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.datasource.auth.AuthDataSource
import com.example.chatappclient.data.remote.dto.auth.login.response.LoginResponseDto
import com.example.chatappclient.data.remote.dto.auth.signup.request.SignupRequestDto
import com.example.chatappclient.data.remote.dto.auth.signup.response.SignupResponseDto
import com.example.chatappclient.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.chatappclient.data.remote.dto.auth.login.request.LoginRequestDto

class AuthRepositoryImpl(private val remote: AuthDataSource) : AuthRepository {

    override suspend fun signup(requestDto: SignupRequestDto): Flow<ResponseResource<SignupResponseDto>> =
        flow {
            when (val response = remote.signup(requestDto)) {
                is ResponseResource.Error -> emit(ResponseResource.error(response.errorMessage))
                is ResponseResource.Success -> emit(ResponseResource.success(response.data))
            }
        }

    override suspend fun login(requestDto: LoginRequestDto): Flow<ResponseResource<LoginResponseDto>> =
        flow {
            when (val response = remote.login(requestDto)) {
                is ResponseResource.Error -> emit(ResponseResource.error(response.errorMessage))
                is ResponseResource.Success -> emit(ResponseResource.success(response.data))
            }
        }

}