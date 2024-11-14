package com.jddev.simpletouch.ui.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CameraCapturePhotoButton(
    modifier: Modifier = Modifier,
    capturing: Boolean,
    onCapture: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .border(4.dp, Color.Gray, CircleShape)
            .clickable(enabled = !capturing) {
                onCapture()
            },
        color = Color.Gray.copy(alpha = 0.5f)
    ) {}
}

@Composable
@Preview
private fun Preview() {
    MaterialTheme {
        CameraCapturePhotoButton(capturing = false, onCapture = {})
    }
}