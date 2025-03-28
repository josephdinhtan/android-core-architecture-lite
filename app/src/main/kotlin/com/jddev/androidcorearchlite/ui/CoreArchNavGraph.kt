package com.jddev.androidcorearchlite.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.AppContainer
import com.jddev.androidcorearchlite.ui.archcatalog.statemachinedemo.StateMachineRoute
import com.jddev.androidcorearchlite.ui.archcatalog.statemachinedemo.StateMachineViewModel
import com.jddev.androidcorearchlite.ui.basic.notification.notificationUiNavGraph
import com.jddev.androidcorearchlite.ui.basic.shareviewmodel.shareViewModelNavGraph
import com.jddev.androidcorearchlite.ui.samepleui.sampleUiNavGraph
import com.jddev.androidcorearchlite.ui.settings.settingsNavGraph
import com.jddev.androidcorearchlite.ui.uicatalog.uiCatalogNavGraph
import com.jddev.simpletouch.ui.foundation.StUiDoubleBackHandler
import com.jddev.simpletouch.ui.navigation.StUiNavHost
import com.jddev.simpletouch.ui.navigation.navigateSingleTop

@Composable
fun CoreArchNavGraph(
    modifier: Modifier = Modifier,
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "nav_home",
) {
    StUiDoubleBackHandler(
        toastMessage = "Press again to exit the app",
    )
    StUiNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            route = "nav_home",
        ) {
            HomeScreen(
                navigateToSettings = {
                    navController.navigateSingleTop("nav_settings")
                },
                navigateToStateMachineDemo = {
                    navController.navigateSingleTop("nav_state_machine")
                },
                navigateToUiCatalog = {
                    navController.navigateSingleTop("nav_ui_catalog")
                },
                navigateToSampleUi = {
                    navController.navigateSingleTop("nav_ui_sampleui")
                },
                navigateToNotification = {
                    navController.navigateSingleTop("notificationUi")
                },
                navigateToShareViewModel = {
                    navController.navigateSingleTop("shareViewModelNavGraph")
                },
            )
        }
        uiCatalogNavGraph("nav_ui_catalog", navController, appContainer.appSettings)
        settingsNavGraph("nav_settings", navController, appContainer.appSettings)
        composable(
            route = "nav_state_machine",
        ) { backStackEntry ->
            val stateMachineViewModel = hiltViewModel<StateMachineViewModel>(backStackEntry)
            StateMachineRoute(
                stateMachineViewModel = stateMachineViewModel,
                onBack = { navController.popBackStack() },
            )
        }
        sampleUiNavGraph("nav_ui_sampleui", navController, appContainer.appSettings)
        notificationUiNavGraph("notificationUi", navController, appContainer.appSettings)
        shareViewModelNavGraph("shareViewModelNavGraph", navController, appContainer.appSettings)
    }
}
