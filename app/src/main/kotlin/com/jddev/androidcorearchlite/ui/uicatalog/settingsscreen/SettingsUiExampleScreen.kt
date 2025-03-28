package com.jddev.androidcorearchlite.ui.uicatalog.settingsscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LooksOne
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
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.checkbox.StSettingsCheckBoxItem
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem
import com.jddev.simpletouch.ui.foundation.topappbar.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.topappbar.stUiLargeTopAppbarScrollBehavior
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsUiExampleScreen(
    onBack: () -> Unit
) {
    val scrollBehavior = stUiLargeTopAppbarScrollBehavior()
    var toggleChecked by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            StUiLargeTopAppBar(
                scrollBehavior = scrollBehavior, title = "Material Settings", onBack = onBack
            )
        },
    ) {
        StSettingsUi(
            modifier = Modifier.padding(it),
            scrollBehavior = scrollBehavior,
        ) {
            StSettingsGroup(
                header = "Personalization"
            ) {
                StSettingsSwitchItem(leadingImageVector = Icons.Outlined.DarkMode,
                    title = "Dark mode",
                    checked = toggleChecked,
                    onCheckedChange = { toggleChecked = !toggleChecked })
                StSettingsSwitchItem(leadingImageVector = Icons.Outlined.DarkMode,
                    title = "Dark mode",
                    checked = toggleChecked,
                    onCheckedChange = { toggleChecked = !toggleChecked })
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.ColorLens,
                    title = "Security",
                    onClick = {})
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.Language,
                    title = "Language",
                    onClick = {})
                StSettingsCheckBoxItem(
                    leadingImageVector = Icons.Outlined.LooksOne,
                    title = "Enable One",
                    subTitle = "Enable One will help something",
                    checked = !toggleChecked,
                    onCheckedChange = { checked -> toggleChecked = !checked },
                )
                StSettingsCheckBoxItem(
                    leadingImageVector = Icons.Outlined.LooksOne,
                    title = "Enable One",
                    subTitle = "Enable One will help something",
                    checked = !toggleChecked,
                    circleShape = true,
                    onCheckedChange = { checked -> toggleChecked = !checked },
                )
            }
            StSettingsGroup(
                header = "Account"
            ) {
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.PersonOutline,
                    title = "Profile & Accounts",
                    onClick = {})
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.Shield,
                    title = "Security",
                    onClick = {})
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Lock,
                    title = "Privacy & Security",
                    onClick = {})
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Lock,
                    title = "Privacy & Security",
                    onClick = {})
            }
            StSettingsGroup(
                header = "No Leading icon"
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
        SettingsUiExampleScreen(onBack = {})
    }
}