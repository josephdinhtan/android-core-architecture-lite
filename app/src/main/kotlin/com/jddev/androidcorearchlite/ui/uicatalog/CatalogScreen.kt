package com.jddev.androidcorearchlite.ui.uicatalog

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
fun CatalogScreen(
    navController: NavController = rememberNavController(),
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
            uiStyle = StSettingsUiStyle.Material
        ) {
            StSettingsGroup(title = "Navigation") {
                StSettingsNavigateItem(
                    title = "Bottom Navigation",
                    onClick = { navController.navigate(CatalogNavigation.BottomNav.route) }
                )
                StSettingsNavigateItem(
                    title = "Horizontal Pager",
                    onClick = { navController.navigate(CatalogNavigation.HorizontalPagerNav.route) }
                )
                StSettingsNavigateItem(
                    title = "Intelligent Charging",
                    onClick = { navController.navigate(CatalogNavigation.IntelligentCharging.route) }
                )
            }
        }
    }
}