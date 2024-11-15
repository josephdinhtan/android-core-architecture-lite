package com.jddev.androidcorearchlite.ui.catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.foundation.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.StUiScrollBehavior
import com.jddev.simpletouch.ui.settingsui.SettingsGroup
import com.jddev.simpletouch.ui.settingsui.SettingsNavigateItem
import com.jddev.simpletouch.ui.settingsui.SettingsUi
import com.jddev.simpletouch.ui.settingsui.UiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navController: NavController = rememberNavController(),
    onBack: () -> Unit
) {
    val scrollBehavior = StUiScrollBehavior()
    Scaffold(
        topBar = {
            StUiLargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = "UI catalog",
                onBack = onBack,
            )
        },
    ) {
        SettingsUi(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), scrollBehavior = scrollBehavior,
            uiStyle = UiStyle.Cupertino
        ) {
            item {
                SettingsGroup(title = "Page navigation") {
                    SettingsNavigateItem(
                        title = "Bottom Navigation",
                        onClick = { navController.navigate(CatalogNavigation.BottomNav.route) }
                    )
                    SettingsNavigateItem(
                        title = "Horizontal Pager",
                        onClick = { navController.navigate(CatalogNavigation.HorizontalPagerNav.route) }
                    )
                }
            }
        }
    }
}