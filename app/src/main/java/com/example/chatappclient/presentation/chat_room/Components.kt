package com.example.chatappclient.presentation.chat_room

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.chatappclient.common.utils.ENDPOINT_AVATAR
import com.example.chatappclient.domain.model.chat_room.RoomHistoryList


@Composable
fun MessageBubble(
    message: RoomHistoryList.Message,
    isSender: Boolean,
    senderAvatar: String,
    receiverAvatar: String
) {

    val radius =
        if (isSender) RoundedCornerShape(
            topStart = 16.dp,
            bottomStart = 16.dp,
            topEnd = 0.dp,
            bottomEnd = 16.dp
        ) else RoundedCornerShape(
            topStart = 0.dp,
            bottomStart = 16.dp,
            topEnd = 16.dp,
            bottomEnd = 16.dp
        )

    Row(
        modifier = Modifier
            .padding(bottom = 24.dp)
    ) {

        if (isSender.not()) {
            AvatarHead(senderAvatar, receiverAvatar, isSender)
        }

        Box(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 8.dp)
                .background(
                    color = if (isSender)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.primaryContainer,
                    shape = radius
                )
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = message.textMessage.orEmpty(),
                    color = if (isSender)
                        MaterialTheme.colorScheme.onSecondaryContainer
                    else
                        MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Normal
                )
                Box(
                    modifier = Modifier.height(12.dp).fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = message.formattedTime.orEmpty(),
                        textAlign = TextAlign.Right,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp)
                    )
                }
            }
        }

        if (isSender) {
            AvatarHead(senderAvatar, receiverAvatar, true)
        }
    }
}


@Preview
@Composable
private fun MessageBubblePreview() {
    MessageBubble(
        message = RoomHistoryList.Message(
            textMessage = "hello",
            formattedTime = "11:00 Pm",
            sessionId = "",
            formattedDate = "",
            timestamp = null,
            sender = "",
            receiver = ""
        ), isSender = true, senderAvatar = null.toString(), receiverAvatar = ""
    )
}

@Composable
fun AvatarHead(
    senderAvatar: String,
    receiverAvatar: String,
    isSender: Boolean
) {
    val avatar =
        if (isSender) "$ENDPOINT_AVATAR$senderAvatar.png" else "$ENDPOINT_AVATAR$receiverAvatar.png"
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = MaterialTheme.colorScheme.onBackground),
            painter = rememberAsyncImagePainter(model = avatar),
            contentDescription = "Friend avatar"
        )

    }
}