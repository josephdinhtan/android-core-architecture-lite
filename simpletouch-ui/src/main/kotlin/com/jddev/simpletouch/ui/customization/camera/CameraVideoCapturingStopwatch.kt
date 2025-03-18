package com.jddev.simpletouch.ui.customization.camera

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FiberManualRecord
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay

@Composable
fun CameraVideoCapturingStopwatch(
    videoPause: Boolean = false,
    videoTimer: String
) {
    Row(
        modifier = Modifier
            .clip(
                RoundedCornerShape(4.dp)
            )
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var fiberShowing by remember { mutableStateOf(true) }
        LaunchedEffect(key1 = true, block = {
            while (true) {
                fiberShowing = !fiberShowing
                delay(500)
            }
        })
        AnimatedContent(
            modifier = Modifier.padding(start = 10.dp, end = 0.dp),
            targetState = fiberShowing,
            contentAlignment = Alignment.Center
        ) { showing ->
            Icon(
                modifier = Modifier.size(10.dp),
                imageVector = Icons.Rounded.FiberManualRecord,
                contentDescription = null,
                tint = if (showing && !videoPause) Color.Red else Color.Transparent
            )
        }
        Text(
            text = videoTimer,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(
                start = 5.dp, end = 10.dp, top = 10.dp, bottom = 10.dp
            )
        )
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        CameraVideoCapturingStopwatch(false, "10:01")
    }
}