package com.jddev.androidcorearchlite.ui.uicatalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.foundation.topappbar.stUiLargeTopAppbarScrollBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    navigateToPager: () -> Unit,
    navigateToBottomNav: () -> Unit,
    navigateToBasicButton: () -> Unit,
    navigateToBasicDialog: () -> Unit,
    navigateToSettingsUi: () -> Unit,
    onBack: () -> Unit
) {
    StUiSimpleScaffold (
        title = "UI catalog",
        onBack = onBack,
    ) {
        StSettingsUi(
            modifier = Modifier
                .fillMaxSize(),
            scrollBehavior = stUiLargeTopAppbarScrollBehavior()
        ) {
            StSettingsGroup(header = "Basic components") {
                StSettingsNavigateItem(
                    title = "Button",
                    onClick = navigateToBasicButton
                )
                StSettingsNavigateItem(
                    title = "Popup dialog",
                    onClick = navigateToBasicDialog
                )
            }
            StSettingsGroup(header = "Navigation") {
                StSettingsNavigateItem(
                    title = "Bottom Navigation",
                    onClick = navigateToBottomNav
                )
                StSettingsNavigateItem(
                    title = "Horizontal Pager",
                    onClick = navigateToPager
                )
            }
            StSettingsGroup(header = "Settings UI") {
                StSettingsNavigateItem(
                    title = "Material Settings",
                    onClick = navigateToSettingsUi
                )
            }
        }
    }
}