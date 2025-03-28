package com.jddev.androidcorearchlite.ui.settings

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jddev.androidcorearchlite.ui.settings.theme.SettingsThemeModeScreen
import com.jddev.simpletouch.ui.utils.sharedViewModel

fun NavGraphBuilder.settingsNavGraph(
    route: String,
    navController: NavHostController,
    appSettings: AppSettings,
) {
    navigation(
        route = route,
        startDestination = "settings_nav_home_route",
    ) {
        composable(
            route = "settings_nav_home_route",
        ) {
            it.sharedViewModel<SettingsViewModel>(navController)
            SettingsRoute(
                appSettings = appSettings,
                onBack = {
                    navController.popBackStack()
                },
                navigateToThemeMode = {
                    navController.navigate("settings_nav_theme_mode_route")
                },
            )
        }

        composable(
            route = "settings_nav_theme_mode_route",
        ) {
            it.sharedViewModel<SettingsViewModel>(navController)
            SettingsThemeModeScreen(appThemeMode = appSettings.appThemeMode.collectAsState().value,
                onBack = {
                    navController.popBackStack()
                },
                onThemeChange = { isDarkMode ->
                    appSettings.appThemeMode.value = isDarkMode
                })
        }
    }
}