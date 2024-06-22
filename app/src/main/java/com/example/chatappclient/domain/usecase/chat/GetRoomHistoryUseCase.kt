package com.example.chatappclient.domain.usecase.chat

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.domain.mapper.toRoomHistoryList
import com.example.chatappclient.domain.model.chat_room.RoomHistoryList
import com.example.chatappclient.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRoomHistoryUseCase(private val repository: ChatRepository) {

    suspend operator fun invoke(receiver: String):
            Flow<ResponseResource<RoomHistoryList>> = flow {
        repository.getRoomHistory(receiver).collect {
            val responseResource = when (it) {
                is ResponseResource.Error -> ResponseResource.error(it.errorMessage.toRoomHistoryList())
                is ResponseResource.Success -> ResponseResource.success(it.data.toRoomHistoryList())
            }
            emit(responseResource)
        }
    }
}