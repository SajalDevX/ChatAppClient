package com.example.chatappclient.common.utils

const val MACHINE_IP = "192.168.61.69" // This is your laptop's network public ip
const val BASE_URL = "http://$MACHINE_IP:8080"
const val ENDPOINT_LOGIN = "$BASE_URL/auth/login"
const val ENDPOINT_SIGNUP = "$BASE_URL/auth/signup"
const val ENDPOINT_FRIEND_LIST = "$BASE_URL/chat/friends-list"
const val ENDPOINT_CHAT_HISTORY = "$BASE_URL/chat/chat-history"
const val ENDPOINT_CHAT_CONNECT = "ws://$MACHINE_IP:8080/chat/connect"

val ENDPOINT_AVATAR = "https://avatars.dicebear.com/api/avataaars/"
