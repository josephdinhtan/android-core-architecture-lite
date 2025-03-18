package com.jddev.simpletouch.ui.customization.settingsui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

internal val LocalStSettingsUiStyle: ProvidableCompositionLocal<StSettingsUiStyle> =
    compositionLocalOf { StSettingsUiStyle.Material }

enum class StSettingsUiStyle {
    Cupertino, Material, OneUi,
}