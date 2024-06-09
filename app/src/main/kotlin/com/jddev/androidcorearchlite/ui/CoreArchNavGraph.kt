package com.jddev.androidcorearchlite.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.app.AppContainer
import com.jddev.androidcorearchlite.ui.home.HomeRoute
import com.jddev.androidcorearchlite.ui.home.HomeViewModel
import com.jddev.androidcorearchlite.ui.settings.SettingsRoute
import com.jddev.androidcorearchlite.ui.settings.SettingsViewModel

@Composable
fun CoreArchNavGraph(
    modifier: Modifier = Modifier,
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    navigationActions: CoreArchNavigationActions,
    startDestination: String = CoreArchDestinations.HOME_ROUTE,
) {
    // DoubleBackPressToExit()
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
    }
}

private fun NavGraphBuilder.composableSlideTransition(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    val tweenDuration = 400
    composable(
        route = route,
        content = content,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(tweenDuration),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(tweenDuration),
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(tweenDuration),
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(tweenDuration),
            )
        },
    )
}
