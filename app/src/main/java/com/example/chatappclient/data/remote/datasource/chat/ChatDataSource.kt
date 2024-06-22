package com.example.chatappclient.data.remote.datasource.chat

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.chat_room.ChatRoomResponseDto
import com.example.chatappclient.data.remote.dto.chat_room.MessageResponseDto
import com.example.chatappclient.data.remote.dto.friend_list.FriendListResponseDto
import kotlinx.coroutines.flow.Flow

interface ChatDataSource {
    suspend fun getFriendList(token: String?): ResponseResource<FriendListResponseDto>
    suspend fun getRoomHistory(
        token: String?,
        receiver: String
    ): ResponseResource<ChatRoomResponseDto>

    suspend fun connectToSocket(
        sender: String,
        receiver: String,
        token: String
    ): ResponseResource<String>

    suspend fun sendMessage(message: String)
    fun receiveMessage(): Flow<MessageResponseDto>
    suspend fun disconnectSocket()
}