package com.jddev.androidcorearchlite.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.theme.StUiTheme

@Composable
fun CoreArchApp(appContainer: com.jddev.androidcorearchlite.AppContainer) {
    StUiTheme {
        val navController = rememberNavController()
        CoreArchNavGraph(
            appContainer = appContainer,
            navController = navController,
        )
    }
}