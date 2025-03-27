package com.jddev.androidcorearchlite.ui.uicatalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.foundation.topappbar.stUiEnterAlwaysScrollBehavior

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
    StUiSimpleScaffold (
        title = "UI catalog",
        onBack = onBack,
    ) {
        StSettingsUi(
            modifier = Modifier
                .fillMaxSize(),
            uiStyle = settingsUiStyle,
            scrollBehavior = stUiEnterAlwaysScrollBehavior()
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