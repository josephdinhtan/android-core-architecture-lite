package com.jddev.androidcorearchlite.ui.settings

import com.jddev.androidcorearchlite.ui.settings.theme.AppThemeMode
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle
import kotlinx.coroutines.flow.MutableStateFlow

data class AppSettings (
    val appThemeMode: MutableStateFlow<AppThemeMode>,
    val uiStyle: MutableStateFlow<StSettingsUiStyle>,
) {
    companion object {
        val Default = AppSettings(
            appThemeMode = MutableStateFlow(AppThemeMode.SYSTEM),
            uiStyle = MutableStateFlow(StSettingsUiStyle.Cupertino),
        )
    }
}