package com.jddev.simpletouch.ui.icon

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.jddev.simpletouch.ui.R
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun SignalCellularBarIcon(
    modifier: Modifier = Modifier,
    level: Int, // -1 to 4
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    val batteryIconRes = when {
        level < 0  -> R.drawable.signal_cellular_off
        level == 0 -> R.drawable.signal_cellular_0_bar
        level == 1 -> R.drawable.signal_cellular_1_bar
        level == 2 -> R.drawable.signal_cellular_2_bar
        level == 3 -> R.drawable.signal_cellular_3_bar
        level == 4 -> R.drawable.signal_cellular_4_bar
        else -> R.drawable.signal_cellular_4_bar
    }
    Image(
        painterResource(batteryIconRes),
        contentDescription = "signal_cellular_bar",
        contentScale = ContentScale.Fit,
        modifier = modifier,
        colorFilter = ColorFilter.tint(color = color)
    )
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        SignalCellularBarIcon(level = 3)
    }
}