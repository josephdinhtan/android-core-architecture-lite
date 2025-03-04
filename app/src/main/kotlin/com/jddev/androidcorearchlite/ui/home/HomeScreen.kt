package com.jddev.androidcorearchlite.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.StUiScrollBehavior
import com.jddev.simpletouch.ui.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.settingsui.StSettingsNavigateItem
import com.jddev.simpletouch.ui.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToSettings: () -> Unit,
    navigateToUiCatalog: () -> Unit,
    navigateToSampleUi: () -> Unit,
    navigateToStateMachineDemo: () -> Unit,
) {
    val scrollBehavior = StUiScrollBehavior()
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
            uiStyle = StSettingsUiStyle.Material,
        ) {
            StSettingsGroup(
                title = "Functionalities"
            ) {
                StSettingsNavigateItem(
                    leadingIcon = Icons.Default.RocketLaunch,
                    title = "Demo",
                    subTitle = "State machine...",
                    onClick = navigateToStateMachineDemo,
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
                    leadingIcon = Icons.Default.Interests,
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
        HomeScreen(modifier = Modifier, {}, {}, {}, {})
    }
}