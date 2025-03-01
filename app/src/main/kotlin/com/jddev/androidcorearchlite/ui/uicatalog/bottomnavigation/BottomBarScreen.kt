package com.jddev.androidcorearchlite.ui.uicatalog.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavScreen(
    onBack: () -> Unit,
) {
    val navController = rememberNavController()
    BottomNavigation(navController = navController) {
        BottomNavGraph(
            navController = navController,
            onBack = onBack
        )
    }
}