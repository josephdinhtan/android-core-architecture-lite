package com.jddev.androidcorearchlite.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jddev.androidcorearchlite.ui.settings.theme.AppThemeMode
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.foundation.dialog.StUiEmptyDialog
import com.jddev.simpletouch.ui.foundation.topappbar.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.topappbar.stUiLargeTopAppbarScrollBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    appSettings: AppSettings,
    onBack: () -> Unit,
    navigateToThemeMode: () -> Unit,
) {
    val scrollBehavior = stUiLargeTopAppbarScrollBehavior()
    val themeMode = appSettings.appThemeMode.collectAsState()

    var showUiStyleDialog by remember { mutableStateOf(false) }

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
        ) {
            StSettingsGroup(header = "General") {
                StSettingsNavigateItem(
                    title = "Theme",
                    value = when (themeMode.value) {
                        AppThemeMode.DARK -> "Dark"
                        AppThemeMode.LIGHT -> "Light"
                        AppThemeMode.SYSTEM -> "System"
                    },
                    leadingImageVector = Icons.Outlined.DarkMode,
                    onClick = navigateToThemeMode,
                )
                StSettingsNavigateItem(
                    title = "Ui style",
                    subTitle = "Popup",
                    leadingImageVector = Icons.Outlined.Style,
                    onClick = { showUiStyleDialog = true },
                )
            }
            StSettingsGroup(header = "Support & Feedback") {
                StSettingsNavigateItem(
                    title = "Report an issue",
                    leadingImageVector = Icons.Outlined.Flag,
                    onClick = {},
                )
                StSettingsNavigateItem(
                    title = "Chat with us",
                    leadingImageVector = Icons.Outlined.ChatBubbleOutline,
                    onClick = {},
                )
                StSettingsNavigateItem(
                    title = "About us",
                    leadingImageVector = Icons.Outlined.Info,
                    onClick = {},
                )
            }
        }
    }

    StUiEmptyDialog(
        showDialog = showUiStyleDialog,
        onDismissRequest = { showUiStyleDialog = false }
    ) {
        Column(Modifier.padding(16.dp)) {

        }
    }
}