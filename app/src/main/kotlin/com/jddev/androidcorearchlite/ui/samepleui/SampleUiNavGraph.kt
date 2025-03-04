package com.jddev.androidcorearchlite.ui.samepleui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.FloatingWindowRoute
import com.jddev.simpletouch.ui.foundation.StUiNavHost

sealed class SampleUiNavigation(val route: String) {
    data object SampleUiHome : SampleUiNavigation("sampleui_home_route")
    data object BubbleMessenger : SampleUiNavigation("bubble_messenger_route")
}

@Composable
fun SampleUiNavGraph(
    modifier: Modifier = Modifier,
    startDestination: SampleUiNavigation = SampleUiNavigation.SampleUiHome,
    onBack: () -> Unit,
) {
    val navController = rememberNavController()
    StUiNavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        composable(
            route = SampleUiNavigation.SampleUiHome.route,
        ) {
            SampleUiScreen(
                navController = navController,
                onBack = onBack,
            )
        }
        composable(
            route = SampleUiNavigation.BubbleMessenger.route,
        ) {
            FloatingWindowRoute (
                onBack = onBack,
            )
        }
    }
}
