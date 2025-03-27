package com.jddev.androidcorearchlite.ui.uicatalog.settingsscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.topappbar.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.topappbar.stUiEnterAlwaysScrollBehavior
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle
import com.jddev.simpletouch.ui.customization.settingsui.checkbox.StSettingsCheckBoxItem
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsUiExampleScreen(
    uiStyle: StSettingsUiStyle, onBack: () -> Unit
) {
    val scrollBehavior = stUiEnterAlwaysScrollBehavior()
    var toggleChecked by remember { mutableStateOf(true) }
    val title = when (uiStyle) {
        StSettingsUiStyle.Cupertino -> "Cupertino Settings"
        else -> "Material Settings"
    }
    Scaffold(
        topBar = {
            StUiLargeTopAppBar(
                scrollBehavior = scrollBehavior, title = title, onBack = onBack
            )
        },
    ) {
        StSettingsUi(
            modifier = Modifier.padding(it),
            scrollBehavior = scrollBehavior,
            uiStyle = uiStyle,
        ) {
            StSettingsGroup(
                title = "Personalization"
            ) {
                StSettingsSwitchItem(leadingIcon = Icons.Outlined.DarkMode,
                    title = "Dark mode",
                    checked = toggleChecked,
                    onCheckedChange = { toggleChecked = !toggleChecked })
                StSettingsNavigateItem(leadingIcon = Icons.Outlined.ColorLens,
                    title = "Security",
                    onClick = {})
                StSettingsNavigateItem(leadingIcon = Icons.Outlined.Language,
                    title = "Language",
                    onClick = {})
                StSettingsCheckBoxItem(
                    leadingIcon = Icons.Default.LooksOne,
                    title = "Enable One",
                    subTitle = "Enable One will help something",
                    checked = !toggleChecked,
                    onCheckedChange = { checked -> toggleChecked = !checked },
                )
                StSettingsCheckBoxItem(
                    leadingIcon = Icons.Default.LooksOne,
                    title = "Enable One",
                    subTitle = "Enable One will help something",
                    checked = !toggleChecked,
                    circleShape = true,
                    onCheckedChange = { checked -> toggleChecked = !checked },
                )
            }
            StSettingsGroup(
                title = "Account"
            ) {
                StSettingsNavigateItem(leadingIcon = Icons.Outlined.PersonOutline,
                    title = "Profile & Accounts",
                    onClick = {})
                StSettingsNavigateItem(leadingIcon = Icons.Outlined.Shield,
                    title = "Security",
                    onClick = {})
                StSettingsNavigateItem(leadingIcon = Icons.Outlined.Lock,
                    title = "Privacy & Security",
                    onClick = {})
                StSettingsNavigateItem(leadingIcon = Icons.Outlined.Lock,
                    title = "Privacy & Security",
                    onClick = {})
            }
            StSettingsGroup(
                title = "No Leading icon"
            ) {
                StSettingsNavigateItem(title = "Profile & Accounts", onClick = {})
                StSettingsNavigateItem(title = "Security", onClick = {})
                StSettingsNavigateItem(title = "Privacy & Security", onClick = {})
                StSettingsNavigateItem(title = "Privacy & Security", onClick = {})
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        SettingsUiExampleScreen(uiStyle = StSettingsUiStyle.Cupertino, onBack = {})
    }
}