
package com.jddev.androidcorearchlite.ui.settings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsRoute(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    appSettings: AppSettings,
    onBack: () -> Unit,
    navigateToThemeMode: () -> Unit,
) {
    SettingsScreen(
        appSettings = appSettings,
        navigateToThemeMode = navigateToThemeMode,
        onBack = onBack,
    )
}