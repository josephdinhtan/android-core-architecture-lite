package com.jddev.simpletouch.ui.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateSingleTop(route: String) =
    this.navigate(route) { launchSingleTop = true }