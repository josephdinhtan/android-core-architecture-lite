package com.jddev.androidcorearchlite.ui

import androidx.navigation.NavHostController

object CoreArchDestinations {
    const val HOME_ROUTE = "home"
    const val SETTINGS_ROUTE = "settings"

    const val STATE_MACHINE_DEMO_ROUTE = "state_machine_demo"
}

class CoreArchNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(CoreArchDestinations.HOME_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSettings: () -> Unit = {
        navController.navigate(CoreArchDestinations.SETTINGS_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToStateMachineDemo: () -> Unit = {
        navController.navigate(CoreArchDestinations.STATE_MACHINE_DEMO_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
}