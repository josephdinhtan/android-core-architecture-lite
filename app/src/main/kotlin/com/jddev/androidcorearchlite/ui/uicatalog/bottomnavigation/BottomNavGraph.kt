package com.jddev.androidcorearchlite.ui.uicatalog.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jddev.simpletouch.ui.foundation.StUiDoubleBackHandler
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavGraph(
    onBack: () -> Unit,
    navController: NavHostController,
) {
    StUiDoubleBackHandler()
    NavHost(
        navController = navController,
        startDestination = NavRoute.LibraryHome,
    ) {
        composable<NavRoute.LibraryHome> {
            BottomNavContent(
                "Library",
            )
        }
        composable<NavRoute.Search> {
            BottomNavContent(
                "Search",
            )
        }
        composable<NavRoute.Settings> {
            BottomNavContent(
                "Settings",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomNavContent(title: String) {
    Scaffold (
        topBar = {
            StUiTopAppBar(
                title = title,
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = title)
        }
    }
}