package com.example.chatappclient.data.repository

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.datasource.auth.AuthDataSource
import com.example.chatappclient.data.remote.dto.auth.login.response.LoginResponseDto
import com.example.chatappclient.data.remote.dto.auth.signup.request.SignupRequestDto
import com.example.chatappclient.data.remote.dto.auth.signup.response.SignupResponseDto
import com.example.chatappclient.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto

class AuthRepositoryImpl(
    private val datasource:AuthDataSource
) : AuthRepository {
    override suspend fun signup(requestDto: SignupRequestDto): Flow<ResponseResource<SignupResponseDto>> {
        return flow {
            when(val response = datasource.signup(requestDto) ){
                is ResponseResource.Error -> emit(ResponseResource.error(response.errorMessage))
                is ResponseResource.Success -> emit(ResponseResource.success(response.data))
            }
        }
    }

    override suspend fun login(requestDto: LoginRequestDto): Flow<ResponseResource<LoginResponseDto>> {
        return flow {
            when(val response = datasource.login(requestDto) ){
                is ResponseResource.Error -> emit(ResponseResource.error(response.errorMessage))
                is ResponseResource.Success -> emit(ResponseResource.success(response.data))
            }
        }
    }
}