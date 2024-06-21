package com.example.chatappclient.data.remote.datasource.auth

import com.example.chatappclient.common.utils.ENDPOINT_LOGIN
import com.example.chatappclient.common.utils.ENDPOINT_SIGNUP
import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.auth.signup.request.SignupRequestDto
import com.example.chatappclient.data.remote.dto.auth.signup.response.SignupResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto
import com.example.chatappclient.data.remote.dto.auth.login.response.LoginResponseDto

class AuthDataSourceImpl(private val client: HttpClient) : AuthDataSource {

    override suspend fun login(request: LoginRequestDto): ResponseResource<LoginResponseDto> {
        return try {
            val response = client.post(ENDPOINT_LOGIN) {
                setBody(request)
            }.body<LoginResponseDto>()
            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(
                LoginResponseDto
                    (error = LoginResponseDto.Error("Oops, something bad happened :("))
            )
        }
    }

    override suspend fun signup(request: SignupRequestDto): ResponseResource<SignupResponseDto> {
        return try {
            val response = client.post(ENDPOINT_SIGNUP) {
                setBody(request)
            }.body<SignupResponseDto>()
            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }

        } catch (e: Exception) {
            ResponseResource.error(
                SignupResponseDto
                    (error = SignupResponseDto.Error("Oops, something bad happened :("))
            )
        }
    }
}