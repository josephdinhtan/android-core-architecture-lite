package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jddev.androidcorearchlite.R

@Composable
fun ChatHeadsViewContent() {
    Box(
        modifier = Modifier.size(64.dp),
    ) {
        Image(
            modifier = Modifier.size(64.dp).align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_chat_heads_demo),
            contentDescription = null
        )
    }
}