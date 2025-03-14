package com.jddev.androidcorearchlite.ui.uicatalog.settingsscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.settingsui.StSettingsNavigateItem
import com.jddev.simpletouch.ui.settingsui.StSettingsSwitchItem
import com.jddev.simpletouch.ui.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsUiExampleScreen (
    uiStyle: StSettingsUiStyle,
    onBack: () -> Unit
) {
    StUiSimpleScaffold(
        title = "Material Settings",
        onBack = onBack
    ) {
        StSettingsUi(
            uiStyle = uiStyle,
        ) {
            StSettingsGroup(
                title = "Personalization"
            ) {
                StSettingsSwitchItem(
                    leadingIcon = Icons.Outlined.DarkMode,
                    title = "Dark mode",
                    checked = true,
                    onCheckedChange = {}
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Outlined.ColorLens,
                    title = "Security",
                    onClick = {}
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Outlined.Language,
                    title = "Language",
                    onClick = {}
                )
            }
            StSettingsGroup(
                title = "Account"
            ) {
                StSettingsNavigateItem(
                    leadingIcon = Icons.Outlined.PersonOutline,
                    title = "Profile & Accounts",
                    onClick = {}
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Outlined.Shield,
                    title = "Security",
                    onClick = {}
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Outlined.Lock,
                    title = "Privacy & Security",
                    onClick = {}
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Outlined.Lock,
                    title = "Privacy & Security",
                    onClick = {}
                )
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        SettingsUiExampleScreen(
            uiStyle = StSettingsUiStyle.Cupertino,
            onBack = {}
        )
    }
}