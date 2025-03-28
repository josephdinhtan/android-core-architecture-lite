package com.jddev.androidcorearchlite.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.Interests
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jddev.androidcorearchlite.R
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.foundation.topappbar.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.topappbar.stUiLargeTopAppbarScrollBehavior
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToSettings: () -> Unit,
    navigateToUiCatalog: () -> Unit,
    navigateToSampleUi: () -> Unit,
    navigateToStateMachineDemo: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToShareViewModel: () -> Unit,
) {
    val scrollBehavior = stUiLargeTopAppbarScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            StUiLargeTopAppBar(
                modifier = modifier,
                title = stringResource(R.string.app_name),
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = navigateToSettings) {
                        Icon(Icons.Outlined.Settings, "Settings")
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
        ) {
            StSettingsGroup(
                header = "Architecture sample"
            ) {
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.RocketLaunch,
                    title = "State machine",
                    subTitle = "Demo a simple state machine",
                    onClick = navigateToStateMachineDemo,
                )
            }
            StSettingsGroup(
                header = "Basic functions"
            ) {
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.Notifications,
                    title = "Notification",
                    subTitle = "Notification and navigate to target screen from notification",
                    onClick = navigateToNotification,
                )
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Rounded.Share,
                    title = "Share ViewModel",
                    subTitle = "Using 1 ViewModel instance among screens",
                    onClick = navigateToShareViewModel,
                )
            }
            StSettingsGroup(
                header = "Ui Catalog"
            ) {
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.Interests,
                    title = "Simple Touch UI",
                    subTitle = "Button, Card, Navigation...",
                    onClick = navigateToUiCatalog,
                )
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.AutoGraph,
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
        HomeScreen(modifier = Modifier, {}, {}, {}, {}, {}, {})
    }
}