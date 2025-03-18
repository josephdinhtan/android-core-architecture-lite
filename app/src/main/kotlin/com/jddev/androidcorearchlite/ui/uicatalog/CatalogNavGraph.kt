package com.jddev.androidcorearchlite.ui.uicatalog

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jddev.androidcorearchlite.ui.settings.AppSettings
import com.jddev.androidcorearchlite.ui.uicatalog.bottomnavigation.BottomNavScreen
import com.jddev.androidcorearchlite.ui.uicatalog.button.BasicButtonsScreen
import com.jddev.androidcorearchlite.ui.uicatalog.dialog.BasicDialogScreen
import com.jddev.androidcorearchlite.ui.uicatalog.pager.HorizontalPagerDemo
import com.jddev.androidcorearchlite.ui.uicatalog.settingsscreen.SettingsUiExampleScreen
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

fun NavGraphBuilder.uiCatalogNavGraph(
    route: String,
    navController: NavHostController,
    appSettings: AppSettings,
) {
    navigation(
        route = route,
        startDestination = "catalog_nav_home_route",
    ) {
        composable(
            route = "catalog_nav_home_route",
        ) {
            CatalogScreen(
                settingsUiStyle = appSettings.uiStyle.collectAsState().value,
                onBack = { navController.popBackStack() },
                navigateToPager = {
                    navController.navigate("catalog_nav_horizontal_pager_route")
                },
                navigateToBottomNav = {
                    navController.navigate("catalog_nav_bottom_nav_route")
                },
                navigateToBasicButton = {
                    navController.navigate("catalog_nav_basic_button_route")
                },
                navigateToBasicDialog = {
                    navController.navigate("catalog_nav_basic_dialog_route")
                },
                navigateToSettingsUi = { uiStyle ->
                    val styleId = when (uiStyle) {
                        StSettingsUiStyle.Cupertino -> 0
                        else -> 1
                    }
                    navController.navigate("catalog_nav_settings_ui_route/$styleId")
                }
            )
        }
        composable(
            route = "catalog_nav_basic_button_route",
        ) {
            BasicButtonsScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_basic_dialog_route",
        ) {
            BasicDialogScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_bottom_nav_route",
        ) {
            BottomNavScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_horizontal_pager_route",
        ) {
            HorizontalPagerDemo(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "catalog_nav_settings_ui_route/{uiStyle}",
        ) {
            val uiStyle = when (it.arguments?.getString("uiStyle")) {
                "0" -> StSettingsUiStyle.Cupertino
                else -> StSettingsUiStyle.Material
            }
            SettingsUiExampleScreen(
                uiStyle = uiStyle,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
