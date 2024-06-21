package com.example.chatappclient.domain.usecase.auth

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.auth.signup.request.SignupRequestDto
import com.example.chatappclient.domain.mapper.toSignupResponse
import com.example.chatappclient.domain.model.auth.SignupResponse
import com.example.chatappclient.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignupUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(request: SignupRequestDto): Flow<ResponseResource<SignupResponse>> =
        flow {
            repository.signup(request).collect {
                when (it) {
                    is ResponseResource.Error -> emit(ResponseResource.error(it.errorMessage.toSignupResponse()))
                    is ResponseResource.Success -> emit(ResponseResource.success(it.data.toSignupResponse()))
                }
            }
        }
}