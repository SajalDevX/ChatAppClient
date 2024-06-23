package com.example.chatappclient.data.repository

import com.example.chatappclient.common.remote.SessionPrefs
import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.datasource.chat.ChatDataSource
import com.example.chatappclient.data.remote.dto.chat_room.ChatRoomResponseDto
import com.example.chatappclient.data.remote.dto.chat_room.MessageResponseDto
import com.example.chatappclient.data.remote.dto.friend_list.FriendListResponseDto
import com.example.chatappclient.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatRepositoryImpl(
    private val datasource: ChatDataSource,
    private val sessionPrefs: SessionPrefs
) : ChatRepository {
    override suspend fun getFriendList(): Flow<ResponseResource<FriendListResponseDto>> {
        return flow {
            val responseResult =
                when (val response = datasource.getFriendList(sessionPrefs.getUser()?.token)) {
                    is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
                    is ResponseResource.Success -> ResponseResource.success(response.data)
                }
            emit(responseResult)
        }
    }

    override suspend fun getRoomHistory(receiver: String): Flow<ResponseResource<ChatRoomResponseDto>> {
        return flow {
            val responseResult = when (val response =
                datasource.getRoomHistory(sessionPrefs.getUser()?.token, receiver)) {
                is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
                is ResponseResource.Success -> ResponseResource.success(response.data)
            }
            emit(responseResult)
        }
    }

    override suspend fun connectToSocket(
        sender: String,
        receiver: String
    ): ResponseResource<String> {
        return when (val response =
            datasource.connectToSocket(sender, receiver, sessionPrefs.getUser()?.token.orEmpty())) {
            is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
            is ResponseResource.Success -> ResponseResource.success(response.data)
        }
    }

    override suspend fun sendMessage(message: String) {
        datasource.sendMessage(message)
    }

    override fun receiveMessage(): Flow<MessageResponseDto> {
        return datasource.receiveMessage()
    }

    override suspend fun disconnectSocket() {
        datasource.disconnectSocket()
    }
}