package com.jddev.androidcorearchlite.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.AppContainer
import com.jddev.androidcorearchlite.ui.catalog.CatalogNavGraph
import com.jddev.androidcorearchlite.ui.home.HomeRoute
import com.jddev.androidcorearchlite.ui.settings.SettingsRoute
import com.jddev.androidcorearchlite.ui.statemachinedemo.StateMachineRoute
import com.jddev.androidcorearchlite.ui.statemachinedemo.StateMachineViewModel
import com.jddev.simpletouch.ui.foundation.StDoubleBackPressToExit
import com.jddev.simpletouch.ui.foundation.StUiNavHost

@Composable
fun CoreArchNavGraph(
    modifier: Modifier = Modifier,
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    navigationActions: CoreArchNavigationActions,
    startDestination: String = ScreenNavigation.Home.route,
) {
    StDoubleBackPressToExit()
    StUiNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            route = ScreenNavigation.Home.route,
        ) {
            HomeRoute(
                navigateToSettings = navigationActions.navigateToSettings,
                navigateToStateMachineDemo = navigationActions.navigateToStateMachineDemo,
                navigateToUiCatalog = navigationActions.navigateToUiCatalog,
            )
        }
        composable(
            route = ScreenNavigation.UiCatalog.route,
        ) {
            CatalogNavGraph(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = ScreenNavigation.Settings.route,
        ) {
            SettingsRoute(
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = ScreenNavigation.StateMachine.route,
        ) { backStackEntry ->
            val stateMachineViewModel = hiltViewModel<StateMachineViewModel>(backStackEntry)
            StateMachineRoute(
                stateMachineViewModel = stateMachineViewModel,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
