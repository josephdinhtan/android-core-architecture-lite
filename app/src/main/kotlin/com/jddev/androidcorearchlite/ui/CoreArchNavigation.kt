package com.jddev.androidcorearchlite.ui

import androidx.navigation.NavHostController

sealed class ScreenNavigation(val route: String) {

    data object Home : ScreenNavigation("home")
    data object UiCatalog : ScreenNavigation("ui_catalog_nav")
    data object SampleUi : ScreenNavigation("ui_sampleui_nav")
    data object Settings : ScreenNavigation("settings")
    data object StateMachine : ScreenNavigation("state_machine_demo")
    data object Details : ScreenNavigation("details/{itemId}") {
        fun createRoute(itemId: String) = "details/$itemId"
    }
}

class CoreArchNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(ScreenNavigation.Home.route) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToUiCatalog: () -> Unit = {
        navController.navigate(ScreenNavigation.UiCatalog.route) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSampleUi: () -> Unit = {
        navController.navigate(ScreenNavigation.SampleUi.route) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSettings: () -> Unit = {
        navController.navigate(ScreenNavigation.Settings.route) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToStateMachineDemo: () -> Unit = {
        navController.navigate(ScreenNavigation.StateMachine.route) {
            launchSingleTop = true
            restoreState = true
        }
    }
}