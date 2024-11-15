package com.jddev.androidcorearchlite.ui.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.ui.catalog.bottomnavigation.BottomNavGraph
import com.jddev.androidcorearchlite.ui.catalog.pager.HorizontalPagerDemo
import com.jddev.simpletouch.ui.foundation.StUiNavHost

sealed class CatalogNavigation(val route: String) {
    data object CatalogHome : CatalogNavigation("catalog_home_route")
    data object BottomNav : CatalogNavigation("bottom_route")
    data object HorizontalPagerNav : CatalogNavigation("horizontal_pager_route")
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
            BottomNavGraph(
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
    }
}
