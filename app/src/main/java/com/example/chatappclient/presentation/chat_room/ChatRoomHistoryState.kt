package com.example.chatappclient.presentation.chat_room

import com.example.chatappclient.domain.model.chat_room.RoomHistoryList

data class ChatRoomHistoryState(
    val loading: Boolean = false,
    val data: List<RoomHistoryList.Message> = emptyList(),
    val error: String = ""
)
