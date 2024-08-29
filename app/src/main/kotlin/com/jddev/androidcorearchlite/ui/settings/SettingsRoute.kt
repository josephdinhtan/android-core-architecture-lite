package com.jddev.androidcorearchlite.ui.settings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsRoute(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    SettingsScreen(
        onThemeChange = { /* call to settingsViewModel.changeTheme() */ },
        onBack = onBack,
    )
}