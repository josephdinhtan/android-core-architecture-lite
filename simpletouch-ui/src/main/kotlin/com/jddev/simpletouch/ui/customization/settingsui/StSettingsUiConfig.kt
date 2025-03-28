package com.jddev.simpletouch.ui.customization.settingsui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalStSettingsUiColor: ProvidableCompositionLocal<StSettingsUiColors> =
    compositionLocalOf { StSettingsUiColors(DefaultColor, DefaultColor, 0.65f) }

internal val LocalStSettingsUiDimension: ProvidableCompositionLocal<StSettingsDimension> =
    compositionLocalOf { StSettingsDimension.Default }

data class StSettingsUiColors(
    val titleColor: Color,
    val subTitleColor: Color,
    val disableAlpha: Float,
) {
    companion object {
        val Default: StSettingsUiColors
            @Composable get(): StSettingsUiColors = StSettingsUiColors(
                titleColor = MaterialTheme.colorScheme.onSurface,
                subTitleColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                disableAlpha = 0.65f
            )
    }
}

data class StSettingsDimension(
    val itemStartPadding: Dp,
    val itemEndPadding: Dp,
    val itemVerticalPadding: Dp,
) {
    companion object {
        val Default: StSettingsDimension
            get(): StSettingsDimension = StSettingsDimension(
                itemStartPadding = 18.dp,
                itemEndPadding = 16.dp,
                itemVerticalPadding = 12.dp,
            )
    }
}

private val DefaultColor = Color(0xFFFF5722)
