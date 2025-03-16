package com.jddev.androidcorearchlite.ui.uicatalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.settingsui.StSettingsNavigateItem
import com.jddev.simpletouch.ui.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    settingsUiStyle: StSettingsUiStyle,
    navigateToPager: () -> Unit,
    navigateToBottomNav: () -> Unit,
    navigateToBasicButton: () -> Unit,
    navigateToBasicDialog: () -> Unit,
    navigateToSettingsUi: (uiStyle: StSettingsUiStyle) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            StUiTopAppBar(
                title = "UI catalog",
                onBack = onBack,
            )
        },
    ) {
        StSettingsUi(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            uiStyle = settingsUiStyle
        ) {
            StSettingsGroup(title = "Basic components") {
                StSettingsNavigateItem(
                    title = "Button",
                    onClick = navigateToBasicButton
                )
                StSettingsNavigateItem(
                    title = "Popup dialog",
                    onClick = navigateToBasicDialog
                )
            }
            StSettingsGroup(title = "Navigation") {
                StSettingsNavigateItem(
                    title = "Bottom Navigation",
                    onClick = navigateToBottomNav
                )
                StSettingsNavigateItem(
                    title = "Horizontal Pager",
                    onClick = navigateToPager
                )
            }
            StSettingsGroup(title = "Settings UI") {
                StSettingsNavigateItem(
                    title = "Material Settings",
                    onClick = { navigateToSettingsUi(StSettingsUiStyle.Material) }
                )
                StSettingsNavigateItem(
                    title = "Cupertino Settings",
                    onClick = { navigateToSettingsUi(StSettingsUiStyle.Cupertino) }
                )
            }
        }
    }
}