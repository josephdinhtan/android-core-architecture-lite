package com.jddev.simpletouch.ui.customization.camera

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FiberManualRecord
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun CameraCaptureVideoButton(
    capturing: Boolean,
    onCapture: () -> Unit,
    onStopCapturing: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .border(4.dp, Color.Gray, CircleShape)
            .clickable {
                if (capturing) onStopCapturing()
                else onCapture()
            }, contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = capturing,
            contentAlignment = Alignment.Center,
            transitionSpec = {
                (scaleIn() togetherWith scaleOut()).using(
                    SizeTransform(clip = false)
                )
            }, label = ""
        ) { capturing ->
            if (capturing) {
                Icon(
                    imageVector = Icons.Rounded.Stop,
                    contentDescription = "Pause video",
                    tint = Color.Red
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.FiberManualRecord,
                    contentDescription = "Capture video",
                    tint = Color.Red,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(1.dp)
                )
            }
        }
    }
}


@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        var isCapturing by remember { mutableStateOf(false) }
        CameraCaptureVideoButton(
            capturing = isCapturing,
            onCapture = { isCapturing = true },
            onStopCapturing = { isCapturing = false }
        )
    }
}