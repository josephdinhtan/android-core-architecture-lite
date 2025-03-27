package com.jddev.simpletouch.ui.foundation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun StUiEmptyDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    StUiBaseDialog(showDialog = showDialog, onDismissRequest = onDismissRequest, content = {
        Box(Modifier
            .pointerInput(Unit) { detectTapGestures { } }
            .shadow(8.dp, shape = RoundedCornerShape(18.dp))
            .width(300.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                MaterialTheme.colorScheme.surface,
            ), contentAlignment = Alignment.Center) {
            content()
        }
    })
}