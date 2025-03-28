package com.jddev.androidcorearchlite.ui.settings

import com.jddev.androidcorearchlite.ui.settings.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableStateFlow

data class AppSettings (
    val appThemeMode: MutableStateFlow<AppThemeMode>,
) {
    companion object {
        val Default = AppSettings(
            appThemeMode = MutableStateFlow(AppThemeMode.SYSTEM),
        )
    }
}