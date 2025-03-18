package com.jddev.simpletouch.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.jddev.simpletouch.ui.customization.settingsui.LocalStSettingsUiStyle
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

object StUi {
    val settingsUiStyle: StSettingsUiStyle
        @Composable
        @ReadOnlyComposable
        get() = LocalStSettingsUiStyle.current
}