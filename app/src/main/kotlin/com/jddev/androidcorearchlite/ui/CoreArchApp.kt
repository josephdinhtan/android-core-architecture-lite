package com.jddev.androidcorearchlite.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.jddev.androidcorearchlite.app.AppContainer
import com.jddev.designsystem.theme.CoreArchTheme

@Composable
fun CoreArchApp(appContainer: AppContainer) {
    CoreArchTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) { CoreArchNavigationActions(navController) }
        CoreArchNavGraph(
            appContainer = appContainer,
            navController = navController,
            navigationActions = navigationActions,
        )
    }
}