package com.jddev.simpletouch.ui.settingsui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

internal val LocalStSettingsUiStyle: ProvidableCompositionLocal<StSettingsUiStyle> =
    compositionLocalOf { StSettingsUiStyle.Material }

enum class StSettingsUiStyle {
    Cupertino, Material, OneUi,
}