package com.example.chatappclient.presentation.chat_room

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatappclient.common.remote.SessionPrefs
import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.chat_room.MessageResponseDto
import com.example.chatappclient.domain.mapper.toMessage
import com.example.chatappclient.domain.repository.ChatRepository
import com.example.chatappclient.domain.usecase.chat.GetRoomHistoryUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class ChatRoomViewModel(
    private val getRoomHistoryUseCase: GetRoomHistoryUseCase,
    private val repository: ChatRepository,
    private val sessionPrefs: SessionPrefs
) : ViewModel() {

    private val _chatState = mutableStateOf(ChatRoomHistoryState())
    val chatState: State<ChatRoomHistoryState> = _chatState

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    fun getUserInfo() = sessionPrefs.getUser()

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun connectToSocket(sender: String, receiver: String) {
        viewModelScope.launch {
            when (val result = repository.connectToSocket(sender, receiver)) {
                is ResponseResource.Error -> _chatState.value =
                    ChatRoomHistoryState(error = result.errorMessage)
                is ResponseResource.Success -> {
                    repository.receiveMessage().onEach { message ->
                        pushMessageToList(message)
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    private fun pushMessageToList(message: MessageResponseDto) {
        val messageList = chatState.value.data.toMutableList().apply {
            add(0, message.toMessage())
        }
        _chatState.value = chatState.value.copy(data = messageList)
    }

    fun sendMessage() {
        viewModelScope.launch {
            if (messageText.value.isNotBlank()) {
                repository.sendMessage(messageText.value)
                _chatState.value=chatState.value.copy()
                _messageText.value = ""
            }
        }
    }

    fun getChatHistory(receiver: String) {
        _chatState.value = ChatRoomHistoryState(loading = true)
        viewModelScope.launch {
            getRoomHistoryUseCase(receiver).collect {
                when (it) {
                    is ResponseResource.Error -> _chatState.value =
                        ChatRoomHistoryState(error = it.errorMessage.errorMessage.orEmpty())
                    is ResponseResource.Success -> _chatState.value =
                        ChatRoomHistoryState(data = it.data.roomData?.sortedByDescending { it.timestamp }
                            .orEmpty())
                }
            }
        }
    }

    fun disconnectSocket() {
        viewModelScope.launch { repository.disconnectSocket() }
    }

    override fun onCleared() {
        super.onCleared()
        disconnectSocket()
    }

}