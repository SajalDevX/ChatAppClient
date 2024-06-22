package com.example.chatappclient.data.remote.datasource.chat

import com.example.chatappclient.common.utils.ENDPOINT_CHAT_CONNECT
import com.example.chatappclient.common.utils.ENDPOINT_CHAT_HISTORY
import com.example.chatappclient.common.utils.ENDPOINT_FRIEND_LIST
import com.example.chatappclient.common.utils.ResponseResource
import com.example.chatappclient.data.remote.dto.chat_room.ChatRoomResponseDto
import com.example.chatappclient.data.remote.dto.chat_room.MessageResponseDto
import com.example.chatappclient.data.remote.dto.friend_list.FriendListResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ChatDataSourceImpl(
    private val client: HttpClient
) : ChatDataSource {

    private var webSocket: WebSocketSession? = null


    override suspend fun getFriendList(token: String?): ResponseResource<FriendListResponseDto> {
        return try {
            val response = client.get(ENDPOINT_FRIEND_LIST) {
                header("Authorization", "Bearer $token")
            }.body<FriendListResponseDto>()
            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(
                FriendListResponseDto
                    (error = FriendListResponseDto.Error("Oops, something bad happened :("))
            )
        }
    }

    override suspend fun getRoomHistory(
        token: String?,
        receiver: String
    ): ResponseResource<ChatRoomResponseDto> {
        return try {
            val response = client.get(ENDPOINT_CHAT_HISTORY) {
                parameter("receiver", receiver)
                header(
                    "Authorization",
                    "Bearer $token"
                )
            }.body<ChatRoomResponseDto>()

            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(
                ChatRoomResponseDto
                    (error = ChatRoomResponseDto.Error("Oops, something bad happened :("))
            )
        }
    }

    override suspend fun connectToSocket(
        sender: String,
        receiver: String,
        token: String
    ): ResponseResource<String> {
        return try {
            println("Websocket: CONNECTING")
            webSocket = client.webSocketSession {
                url(ENDPOINT_CHAT_CONNECT).apply {
                    parameter("sender", sender)
                    parameter("receiver", receiver)
                    header(
                        "Authorization",
                        "Bearer $token"
                    )
                }
            }
            if (webSocket?.isActive == true) {
                println("Websocket: CONNECTED")
                ResponseResource.success("Connected")
            } else {
                println("Websocket: CONNECTING FAILED")
                ResponseResource.error("Couldn't establish a connection.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResource.error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun sendMessage(message: String) {
        try {
            webSocket?.send(Frame.Text(message))
            println("WebSocket: MessageSent -> $message")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun receiveMessage(): Flow<MessageResponseDto> {
        return try {
            webSocket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText().orEmpty()
                    println("WebSocket: Message received -> $json")
                    val messageResponseDto = Json.decodeFromString<MessageResponseDto>(json)
                    messageResponseDto
                } ?: flow { }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyFlow()
        }
    }

    override suspend fun disconnectSocket() {
        webSocket?.close()
        println("WebSocket: CLOSED")
    }
}