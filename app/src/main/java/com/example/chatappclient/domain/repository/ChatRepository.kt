package com.example.chatappclient.domain.repository

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.chat_room.ChatRoomResponseDto
import com.example.chatappclient.data.remote.dto.chat_room.MessageResponseDto
import com.example.chatappclient.data.remote.dto.friend_list.FriendListResponseDto
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getFriendList(): Flow<ResponseResource<FriendListResponseDto>>
    suspend fun getRoomHistory(receiver: String): Flow<ResponseResource<ChatRoomResponseDto>>

    suspend fun connectToSocket(sender: String, receiver: String): ResponseResource<String>
    suspend fun sendMessage(message: String)
    fun receiveMessage(): Flow<MessageResponseDto>
    suspend fun disconnectSocket()
}