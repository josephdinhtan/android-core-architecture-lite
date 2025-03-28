package com.jddev.androidcorearchlite.ui.basic.notification

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.jddev.androidcorearchlite.ui.settings.AppSettings
import com.jddev.simpletouch.ui.navigation.navigateSingleTop

fun NavGraphBuilder.notificationUiNavGraph(
    route: String,
    navController: NavHostController,
    appSettings: AppSettings,
) {
    navigation(
        route = route,
        startDestination = "nested_notificationUi",
    ) {
        composable("nested_notificationUi") {
            NotificationUiRoute(
                navigateToDetailNotification = {
                    navController.navigateSingleTop("notificationUiDetail/from main navigation")
                },
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = "notificationUiDetail/{simple_message}",
            arguments = listOf(navArgument("simple_message") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink {
                uriPattern =
                    "https://jddev.com/notificationUi/notificationUiDetail/simple_message={simple_message}"
            })
        ) { backStackEntry ->
            val arguments = backStackEntry.arguments
            arguments?.getString("simple_message")?.let { message ->
                NotificationUiDetail(
                    detailId = message,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}