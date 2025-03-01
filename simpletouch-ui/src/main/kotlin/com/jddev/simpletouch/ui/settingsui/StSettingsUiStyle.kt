package com.jddev.simpletouch.ui.settingsui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalStUiStyle: ProvidableCompositionLocal<StSettingsUiStyle> = compositionLocalOf { StSettingsUiStyle.Material }

enum class StSettingsUiStyle {
    Cupertino,
    Material,
    OneUi,
}