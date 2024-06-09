package com.jddev.androidcorearchlite.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel,
    onBack: () -> Unit,
) {
    SettingsScreen(
        modifier = modifier,
        onThemeChange = { /* call to settingsViewModel.changeTheme() */ },
        onBack = onBack,
    )
}