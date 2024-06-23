package com.example.chatappclient.presentation.friend_list

import com.example.chatappclient.domain.model.friend_list.FriendList


data class FriendListState(
    val isLoading: Boolean = false,
    val data: List<FriendList.FriendInfo> = emptyList(),
    val error: String = ""
)
