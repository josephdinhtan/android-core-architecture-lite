package com.jddev.androidcorearchlite.ui.uicatalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.ui.uicatalog.bottomnavigation.BottomNavScreen
import com.jddev.androidcorearchlite.ui.uicatalog.intelligentcharging.IntelligentChargingRoute
import com.jddev.androidcorearchlite.ui.uicatalog.pager.HorizontalPagerDemo
import com.jddev.simpletouch.ui.foundation.StUiNavHost

sealed class CatalogNavigation(val route: String) {
    data object CatalogHome : CatalogNavigation("catalog_home_route")
    data object BottomNav : CatalogNavigation("bottom_route")
    data object HorizontalPagerNav : CatalogNavigation("horizontal_pager_route")
    data object IntelligentCharging : CatalogNavigation("intelligent_charging_route")
}

@Composable
fun CatalogNavGraph(
    modifier: Modifier = Modifier,
    startDestination: CatalogNavigation = CatalogNavigation.CatalogHome,
    onBack: () -> Unit,
) {
    val navController = rememberNavController()
    StUiNavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        composable(
            route = CatalogNavigation.CatalogHome.route,
        ) {
            CatalogScreen(
                navController = navController,
                onBack = onBack,
            )
        }
        composable(
            route = CatalogNavigation.BottomNav.route,
        ) {
            BottomNavScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = CatalogNavigation.HorizontalPagerNav.route,
        ) {
            HorizontalPagerDemo(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = CatalogNavigation.IntelligentCharging.route,
        ) {
            IntelligentChargingRoute(
                onBack = { navController.popBackStack() },
            )
        }
    }
}
