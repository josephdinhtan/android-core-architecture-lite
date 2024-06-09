package com.jddev.androidcorearchlite.ui

import androidx.navigation.NavHostController

object CoreArchDestinations {
    const val HOME_ROUTE = "home"
    const val SETTINGS_ROUTE = "settings"
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
}