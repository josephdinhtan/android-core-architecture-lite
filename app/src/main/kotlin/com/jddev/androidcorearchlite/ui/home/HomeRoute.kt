package com.jddev.androidcorearchlite.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToUiCatalog: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToStateMachineDemo: () -> Unit,
) {
    HomeScreen(
        navigateToSettings = navigateToSettings,
        navigateToUiCatalog = navigateToUiCatalog,
        navigateToStateMachineDemo = navigateToStateMachineDemo,
    )
}