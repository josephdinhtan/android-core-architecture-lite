package com.jddev.androidcorearchlite.ui.uicatalog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jddev.androidcorearchlite.ui.uicatalog.bottomnavigation.BottomNavScreen
import com.jddev.androidcorearchlite.ui.uicatalog.pager.HorizontalPagerDemo

fun NavGraphBuilder.uiCatalogNavGraph(
    route: String,
    navController: NavHostController,
) {
    navigation(
        route = route,
        startDestination = "catalog_nav_home_route",
    ) {
        composable(
            route = "catalog_nav_home_route",
        ) {
            CatalogScreen(
                onBack = { navController.popBackStack() },
                navigateToPager = {
                    navController.navigate("catalog_nav_horizontal_pager_route")
                },
                navigateToBottomNav = {
                    navController.navigate("catalog_nav_bottom_nav_route")
                }
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
    }
}
