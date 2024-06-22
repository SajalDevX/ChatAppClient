package com.example.chatappclient.presentation.auth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatappclient.common.remote.SessionPrefs
import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.domain.mapper.toUser
import com.example.chatappclient.domain.usecase.auth.LoginUseCase
import com.example.chatappclient.presentation.common.EmailState
import com.example.chatappclient.presentation.common.PasswordState
import kotlinx.coroutines.launch
import com.example.chatappclient.data.remote.dto.auth.login.request.LoginRequestDto

class LoginViewModel(
    private val useCase: LoginUseCase,
    private val sessionPrefs: SessionPrefs
) : ViewModel() {

    private val _emailState = EmailState()
    val emailState: EmailState = _emailState

    private val _passwordState = PasswordState()
    val passwordState: PasswordState = _passwordState

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    fun onEmailChanged(email: String) {
        _emailState.text = email
    }

    fun onPasswordChanged(password: String) {
        _passwordState.text = password
    }

    fun performLogin() {
        if (validateInputs())
            viewModelScope.launch {
                _loginState.value = LoginState(isLoading = true)
                val request = LoginRequestDto(
                    email = emailState.text, password = passwordState.text
                )
                useCase(request).collect {
                    when (it) {
                        is ResponseResource.Error -> _loginState.value =
                            LoginState(error = it.errorMessage.errorMessage.orEmpty())
                        is ResponseResource.Success -> {
                            sessionPrefs.saveUser(it.data.toUser().copy(isLoggedIn = true))
                            _loginState.value =
                                LoginState(data = it.data)
                        }
                    }
                }
            }
    }

    private fun validateInputs(): Boolean {
        if (emailState.text.isEmpty()) {
            _emailState.validate()
            return false
        }
        if (passwordState.text.isEmpty()) {
            _passwordState.validate()
            return false
        }
        return true
    }
}