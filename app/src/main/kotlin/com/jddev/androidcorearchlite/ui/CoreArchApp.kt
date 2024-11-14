package com.jddev.androidcorearchlite.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.theme.StUiTheme

@Composable
fun CoreArchApp(appContainer: com.jddev.androidcorearchlite.AppContainer) {
    StUiTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) { CoreArchNavigationActions(navController) }
        CoreArchNavGraph(
            appContainer = appContainer,
            navController = navController,
            navigationActions = navigationActions,
        )
    }
}