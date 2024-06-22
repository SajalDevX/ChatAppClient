package com.example.chatappclient.data.repository

import com.example.chatappclient.common.remote.SessionPrefs
import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.datasource.chat.ChatDataSource
import com.example.chatappclient.data.remote.dto.chat_room.ChatRoomResponseDto
import com.example.chatappclient.data.remote.dto.chat_room.MessageResponseDto
import com.example.chatappclient.data.remote.dto.friend_list.FriendListResponseDto
import com.example.chatappclient.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class ChatRepositoryImpl(
    private val remote: ChatDataSource,
    private val sessionPrefs: SessionPrefs
) : ChatRepository {    override suspend fun getFriendList(): Flow<ResponseResource<FriendListResponseDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRoomHistory(receiver: String): Flow<ResponseResource<ChatRoomResponseDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun connectToSocket(
        sender: String,
        receiver: String
    ): ResponseResource<String> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun receiveMessage(): Flow<MessageResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun disconnectSocket() {
        TODO("Not yet implemented")
    }
}