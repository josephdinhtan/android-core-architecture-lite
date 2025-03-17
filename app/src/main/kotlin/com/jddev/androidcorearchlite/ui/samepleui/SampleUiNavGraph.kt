package com.jddev.androidcorearchlite.ui.samepleui

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.FloatingWindowRoute
import com.jddev.androidcorearchlite.ui.samepleui.intelligentcharging.IntelligentChargingRoute
import com.jddev.androidcorearchlite.ui.samepleui.snakegame.SnakeGameRoute
import com.jddev.androidcorearchlite.ui.settings.AppSettings

fun NavGraphBuilder.sampleUiNavGraph(
    route: String,
    navController: NavHostController,
    appSettings: AppSettings,
) {
    navigation(
        route = route,
        startDestination = "sampleui_nav_home_route",
    ) {
        composable(
            route = "sampleui_nav_home_route",
        ) {
            SampleUiScreen(
                settingsUiStyle = appSettings.uiStyle.collectAsState().value,
                navigateToBubbleMessenger = {
                    navController.navigate("sampleui_nav_bubble_messenger_route")
                },
                navigateToIntelligentCharging = {
                    navController.navigate("sampleui_nav_intelligent_charging_route")
                },
                navigateToSnakeGame = {
                    navController.navigate("sampleui_nav_snake_game_route")
                },
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(
            route = "sampleui_nav_bubble_messenger_route",
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://jddev.com/floating_window/"
            })
        ) {
            FloatingWindowRoute(
                settingsUiStyle = appSettings.uiStyle.collectAsState().value,
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(
            route = "sampleui_nav_intelligent_charging_route",
            deepLinks = listOf(navDeepLink {
                uriPattern =
                    "https://jddev.com/intelligent_charging/"
            })
        ) {
            IntelligentChargingRoute(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "sampleui_nav_snake_game_route",
        ) {
            SnakeGameRoute(
                onBack = { navController.popBackStack() },
            )
        }
    }
}
