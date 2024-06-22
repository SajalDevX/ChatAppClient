package com.example.chatappclient.domain.usecase.auth

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.domain.mapper.toLoginResponse
import com.example.chatappclient.domain.model.auth.LoginResponse
import com.example.chatappclient.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.chatappclient.data.remote.dto.auth.login.request.LoginRequestDto

class LoginUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(requestDto: LoginRequestDto): Flow<ResponseResource<LoginResponse>> =
        flow {
            repository.login(requestDto).collect {
                when (it) {
                    is ResponseResource.Error -> emit(ResponseResource.error(it.errorMessage.toLoginResponse()))
                    is ResponseResource.Success -> emit(ResponseResource.success(it.data.toLoginResponse()))
                }
            }
        }
}