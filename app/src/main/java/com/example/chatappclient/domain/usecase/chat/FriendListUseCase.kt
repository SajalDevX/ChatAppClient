package com.example.chatappclient.domain.usecase.chat

import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.domain.mapper.toFriendList
import com.example.chatappclient.domain.model.friend_list.FriendList
import com.example.chatappclient.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FriendListUseCase(private val repository: ChatRepository) {

    suspend operator fun invoke(): Flow<ResponseResource<FriendList>> = flow {
        repository.getFriendList().collect {
            val responseResource = when (it) {
                is ResponseResource.Error -> ResponseResource.error(it.errorMessage.toFriendList())
                is ResponseResource.Success -> ResponseResource.success(it.data.toFriendList())
            }
            emit(responseResource)
        }
    }
}