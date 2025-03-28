package com.jddev.androidcorearchlite.ui.samepleui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleUiScreen(
    navigateToBubbleMessenger: () -> Unit,
    navigateToIntelligentCharging: () -> Unit,
    navigateToSnakeGame: () -> Unit,
    navigateTo: (String) -> Unit,
    onBack: () -> Unit
) {
    StUiSimpleScaffold(
        title = "Sample UI",
        onBack = onBack,
    ) {
        StSettingsUi(
            modifier = Modifier.fillMaxSize(),
        ) {
            StSettingsGroup {
                StSettingsNavigateItem(
                    title = "Messenger chat heads", onClick = navigateToBubbleMessenger
                )
            }
            StSettingsGroup {
                StSettingsNavigateItem(
                    title = "Intelligent Charging", onClick = navigateToIntelligentCharging
                )
            }
            StSettingsGroup {
                StSettingsNavigateItem(
                    title = "Fitbit Settings", onClick = { navigateTo("fitbit_settings_ui") }
                )
            }
            StSettingsGroup {
                StSettingsNavigateItem(
                    title = "Snake Game", onClick = navigateToSnakeGame
                )
            }
        }
    }
}