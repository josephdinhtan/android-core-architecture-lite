package com.jddev.androidcorearchlite.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.app.AppContainer
import com.jddev.androidcorearchlite.ui.home.HomeRoute
import com.jddev.androidcorearchlite.ui.home.HomeViewModel
import com.jddev.androidcorearchlite.ui.settings.SettingsRoute
import com.jddev.androidcorearchlite.ui.settings.SettingsViewModel
import com.jddev.androidcorearchlite.ui.statemachinedemo.StateMachineRoute
import com.jddev.androidcorearchlite.ui.statemachinedemo.StateMachineViewModel
import com.jddev.designsystem.component.DoubleBackPressToExit
import com.jddev.designsystem.component.transition.composableSlideTransition

@Composable
fun CoreArchNavGraph(
    modifier: Modifier = Modifier,
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    navigationActions: CoreArchNavigationActions,
    startDestination: String = CoreArchDestinations.HOME_ROUTE,
) {
    DoubleBackPressToExit()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composableSlideTransition(
            route = CoreArchDestinations.HOME_ROUTE,
        ) {
            val homeViewModel = viewModel<HomeViewModel>(
                factory = HomeViewModel.provideFactory()
            )
            HomeRoute(
                homeViewModel = homeViewModel,
                navigateToSettings = navigationActions.navigateToSettings,
                navigateToStateMachineDemo = navigationActions.navigateToStateMachineDemo,
            )
        }
        composableSlideTransition(
            route = CoreArchDestinations.SETTINGS_ROUTE,
        ) {
            val settingsViewModel = viewModel<SettingsViewModel>(
                factory = SettingsViewModel.provideFactory()
            )
            SettingsRoute(
                settingsViewModel = settingsViewModel,
                onBack = { navController.popBackStack() },
            )
        }
        composableSlideTransition(
            route = CoreArchDestinations.STATE_MACHINE_DEMO_ROUTE,
        ) {
            val stateMachineViewModel = viewModel<StateMachineViewModel>(
                factory = StateMachineViewModel.provideFactory(appContainer.waterManager)
            )
            StateMachineRoute(
                stateMachineViewModel = stateMachineViewModel,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
