package com.jddev.androidcorearchlite.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.ui.home.HomeRoute
import com.jddev.androidcorearchlite.ui.settings.SettingsRoute
import com.jddev.androidcorearchlite.ui.statemachinedemo.StateMachineRoute
import com.jddev.androidcorearchlite.ui.statemachinedemo.StateMachineViewModel
import com.simpletouch.ui.component.StDoubleBackPressToExit
import com.simpletouch.ui.component.transition.composableSlideTransition

@Composable
fun CoreArchNavGraph(
    modifier: Modifier = Modifier,
    appContainer: com.jddev.androidcorearchlite.AppContainer,
    navController: NavHostController = rememberNavController(),
    navigationActions: CoreArchNavigationActions,
    startDestination: String = CoreArchDestinations.HOME_ROUTE,
) {
    StDoubleBackPressToExit()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composableSlideTransition(
            route = CoreArchDestinations.HOME_ROUTE,
        ) {
            HomeRoute(
                navigateToSettings = navigationActions.navigateToSettings,
                navigateToStateMachineDemo = navigationActions.navigateToStateMachineDemo,
            )
        }
        composableSlideTransition(
            route = CoreArchDestinations.SETTINGS_ROUTE,
        ) {
            SettingsRoute(
                onBack = { navController.popBackStack() },
            )
        }
        composableSlideTransition(
            route = CoreArchDestinations.STATE_MACHINE_DEMO_ROUTE,
        ) { backStackEntry ->
            val stateMachineViewModel = hiltViewModel<StateMachineViewModel>(backStackEntry)
            StateMachineRoute(
                stateMachineViewModel = stateMachineViewModel,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
