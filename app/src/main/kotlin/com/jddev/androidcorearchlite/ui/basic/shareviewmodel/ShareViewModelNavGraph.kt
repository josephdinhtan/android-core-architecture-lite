package com.jddev.androidcorearchlite.ui.basic.shareviewmodel

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jddev.androidcorearchlite.ui.settings.AppSettings
import com.jddev.simpletouch.ui.navigation.navigateSingleTop
import com.jddev.simpletouch.ui.utils.sharedViewModel

fun NavGraphBuilder.shareViewModelNavGraph(
    route: String,
    navController: NavHostController,
    appSettings: AppSettings,
) {
    navigation(
        route = route,
        startDestination = "mainScreen",
    ) {
        composable("mainScreen") {
            val viewModel = it.sharedViewModel<ShareViewModelViewModel>(navController)
            ShareViewModelMainScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                navigateToNextLevel = { navController.navigateSingleTop("Screen1") },
            )
        }
        composable("Screen1") {
            val viewModel = it.sharedViewModel<ShareViewModelViewModel>(navController)
            ShareViewModelMainScreen(
                viewModel = viewModel,
                level = 1,
                onBack = { navController.popBackStack() },
                navigateToNextLevel = { navController.navigateSingleTop("Screen2") },
            )
        }
        composable("Screen2") {
            val viewModel = it.sharedViewModel<ShareViewModelViewModel>(navController)
            ShareViewModelMainScreen(
                viewModel = viewModel,
                level = 2,
                onBack = { navController.popBackStack() },
                navigateToNextLevel = { },
            )
        }
    }
}