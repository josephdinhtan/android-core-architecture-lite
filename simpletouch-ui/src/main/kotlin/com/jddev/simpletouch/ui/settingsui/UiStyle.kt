package com.jddev.simpletouch.ui.settingsui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalUiStyle: ProvidableCompositionLocal<UiStyle> = compositionLocalOf { UiStyle.Material }

enum class UiStyle {
    Cupertino,
    Material,
    OneUi,
}