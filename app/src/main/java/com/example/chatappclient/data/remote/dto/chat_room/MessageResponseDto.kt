package com.example.chatappclient.data.remote.dto.chat_room


import kotlinx.serialization.Serializable

@Serializable
data class MessageResponseDto(
    val sessionId:String?,
    val receiver: String?,
    val sender: String?,
    val textMessage: String?,
    val timestamp: Long?
)