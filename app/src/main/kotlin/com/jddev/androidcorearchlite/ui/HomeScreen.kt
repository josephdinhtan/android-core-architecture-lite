package com.jddev.androidcorearchlite.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.stUiScrollBehavior
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    settingsUiStyle: StSettingsUiStyle,
    navigateToSettings: () -> Unit,
    navigateToUiCatalog: () -> Unit,
    navigateToSampleUi: () -> Unit,
    navigateToStateMachineDemo: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToShareViewModel: () -> Unit,
) {
    val scrollBehavior = stUiScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            StUiLargeTopAppBar(
                modifier = modifier,
                title = "Android Core Architecture",
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = navigateToSettings) {
                        Icon(Icons.Default.Settings, "Settings")
                    }
                },
            )
        },
    ) { innerPadding ->
        StSettingsUi(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            scrollBehavior = scrollBehavior,
            uiStyle = settingsUiStyle,
        ) {
            StSettingsGroup(
                title = "Architecture"
            ) {
                StSettingsNavigateItem(
                    leadingIcon = Icons.Default.RocketLaunch,
                    title = "State machine",
                    subTitle = "Demo a simple state machine",
                    onClick = navigateToStateMachineDemo,
                )
            }
            StSettingsGroup(
                title = "Basic functions"
            ) {
                StSettingsNavigateItem(
                    leadingIcon = Icons.Default.Notifications,
                    title = "Notification",
                    onClick = navigateToNotification,
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Rounded.Share,
                    title = "Share ViewModel",
                    onClick = navigateToShareViewModel,
                )
            }
            StSettingsGroup(
                title = "Ui Catalog"
            ) {
                StSettingsNavigateItem(
                    leadingIcon = Icons.Default.Interests,
                    title = "Simple Touch UI",
                    subTitle = "Button, Card, Navigation...",
                    onClick = navigateToUiCatalog,
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Default.AutoGraph,
                    title = "Sample UI",
                    subTitle = "Bubble Messenger...",
                    onClick = navigateToSampleUi,
                )
            }
        }
    }
}

@StUiPreview
@Composable
private fun Preview() {
    StUiPreviewWrapper {
        HomeScreen(modifier = Modifier, settingsUiStyle = StSettingsUiStyle.Material,
            {}, {}, {}, {}, {}, {})
    }
}