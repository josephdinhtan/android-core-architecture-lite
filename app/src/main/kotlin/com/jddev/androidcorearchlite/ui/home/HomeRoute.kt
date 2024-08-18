package com.jddev.androidcorearchlite.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navigateToSettings: () -> Unit,
    navigateToStateMachineDemo: () -> Unit,
) {
    HomeScreen(
        modifier = modifier,
        navigateToSettings = navigateToSettings,
        navigateToStateMachineDemo = navigateToStateMachineDemo,
    )
}