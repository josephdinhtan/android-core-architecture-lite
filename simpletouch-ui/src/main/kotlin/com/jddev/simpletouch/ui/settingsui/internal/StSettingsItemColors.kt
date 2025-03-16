package com.jddev.simpletouch.ui.settingsui.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class StSettingsItemColors(
    val titleColor: Color,
    val subTitleColor: Color,
) {
    companion object {
        @Composable
        fun Default(): StSettingsItemColors = StSettingsItemColors(
            titleColor = MaterialTheme.colorScheme.onSurface,
            subTitleColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        )
    }
}
