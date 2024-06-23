package com.example.chatappclient.presentation.friend_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.chatappclient.common.navigateTo
import com.example.chatappclient.common.remote.SessionPrefs
import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.domain.usecase.chat.FriendListUseCase
import com.example.chatappclient.presentation.common.Routes
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class FriendListViewModel(
    private val useCase: FriendListUseCase,
    private val sessionPrefs: SessionPrefs
) : ViewModel() {

    private val _searchState = mutableStateOf("")
    val searchState: State<String> = _searchState

    private val _friendListState = mutableStateOf(FriendListState())
    val friendListState: State<FriendListState> = _friendListState

    fun onSearchTextChange(result: String) {
        // TODO: I'm too lazy to handle search, on other hand you are the hero so have fun :D
        _searchState.value = result
    }

    fun performLogout(navController: NavController) {
        sessionPrefs.clearSession()
        navigateTo(navController, Routes.Login.route, true)
    }

     fun getFriendList() {
        _friendListState.value = FriendListState(isLoading = true)
        viewModelScope.launch {
            useCase().onEach {
                when (it) {
                    is ResponseResource.Error ->
                        _friendListState.value =
                            FriendListState(error = it.errorMessage.errorMessage.orEmpty())
                    is ResponseResource.Success ->
                        _friendListState.value =
                            FriendListState(data = it.data.friendInfo.orEmpty())
                }
            }.launchIn(viewModelScope)
        }
    }
}