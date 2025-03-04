package com.jddev.androidcorearchlite.ui.samepleui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.settingsui.StSettingsNavigateItem
import com.jddev.simpletouch.ui.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleUiScreen(
    navController: NavController = rememberNavController(),
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            StUiTopAppBar(
                title = "Sample UI",
                onBack = onBack,
            )
        },
    ) {
        StSettingsUi(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            uiStyle = StSettingsUiStyle.Material
        ) {
            StSettingsGroup(title = "Bubble UI") {
                StSettingsNavigateItem(
                    title = "Messenger chat heads",
                    onClick = { navController.navigate(SampleUiNavigation.BubbleMessenger.route) }
                )
            }
        }
    }
}