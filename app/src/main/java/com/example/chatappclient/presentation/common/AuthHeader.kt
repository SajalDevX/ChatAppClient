package com.example.chatappclient.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatappclient.R

@Composable
fun AuthHeader(
    title: String,
    painterResource: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

            .clip(
                shape = ShapeDefaults.ExtraLarge.copy(
                    topEnd = CornerSize(0.dp),
                    topStart = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(90.dp)
                )
            )
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Image(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            painter = painterResource(id = painterResource),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

