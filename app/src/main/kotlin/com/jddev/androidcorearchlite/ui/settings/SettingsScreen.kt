package com.jddev.androidcorearchlite.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.foundation.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.StUiScrollBehavior
import com.jddev.simpletouch.ui.settingsui.SettingsSwitchItem
import com.jddev.simpletouch.ui.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.settingsui.StSettingsNavigateItem
import com.jddev.simpletouch.ui.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onThemeChange: () -> Unit,
    onBack: () -> Unit,
) {
    val scrollBehavior = StUiScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            StUiLargeTopAppBar(
                modifier = modifier,
                scrollBehavior = scrollBehavior,
                onBack = onBack,
                title = "Settings",
            )
        },
    ) { innerPadding ->
        StSettingsUi(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            scrollBehavior = scrollBehavior,
            uiStyle = StSettingsUiStyle.Material,
        ) {
            StSettingsGroup(title = "General") {
                StSettingsNavigateItem(
                    title = "Theme",
                    subTitle = "Dark mode",
                    leadingIcon = Icons.Default.DarkMode,
                    onClick = onThemeChange,
                )
                StSettingsNavigateItem(
                    title = "Language",
                    subTitle = "English",
                    leadingIcon = Icons.Default.Language,
                    onClick = onThemeChange,
                )
                SettingsSwitchItem(
                    title = "Switch test",
                    subTitle = "Switch test sub title",
                    leadingIcon = Icons.Default.Language,
                    onCheckedChange = {},
                )
            }
            StSettingsGroup(title = "Other") {
                StSettingsNavigateItem(
                    title = "Theme",
                    subTitle = "Dark mode",
                    leadingIcon = Icons.Default.DarkMode,
                    onClick = onThemeChange,
                )
                StSettingsNavigateItem(
                    title = "Language",
                    subTitle = "English",
                    leadingIcon = Icons.Default.Language,
                    onClick = onThemeChange,
                )
                SettingsSwitchItem(
                    title = "Switch test",
                    subTitle = "Switch test sub title",
                    leadingIcon = Icons.Default.Language,
                    onCheckedChange = {},
                )
            }
            StSettingsGroup(title = "Support & Feedback") {
                StSettingsNavigateItem(
                    title = "Report an issue",
                    leadingIcon = Icons.Default.Flag,
                    onClick = onThemeChange,
                )
                StSettingsNavigateItem(
                    title = "Chat with us",
                    leadingIcon = Icons.AutoMirrored.Filled.Chat,
                    onClick = onThemeChange,
                )
                StSettingsNavigateItem(
                    title = "About us",
                    leadingIcon = Icons.Default.Info,
                    onClick = onThemeChange,
                )
                SettingsSwitchItem(
                    title = "Switch test",
                    subTitle = "Switch test sub title",
                    leadingIcon = Icons.Default.Language,
                    onCheckedChange = {},
                )
            }
        }
    }
}