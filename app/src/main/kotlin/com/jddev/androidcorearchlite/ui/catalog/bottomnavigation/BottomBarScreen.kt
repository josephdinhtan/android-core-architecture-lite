package com.jddev.androidcorearchlite.ui.catalog.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jddev.simpletouch.ui.foundation.StUiNavHost
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.foundation.TransitionType

private sealed class BottomNavScreen(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavScreen("home", Icons.Default.Home, "Home")
    object Search : BottomNavScreen("search", Icons.Default.Search, "Search")
    object Profile : BottomNavScreen("profile", Icons.Default.Person, "Profile")
}

private val items = listOf(BottomNavScreen.Home, BottomNavScreen.Search, BottomNavScreen.Profile)
private val textItems = listOf("Songs", "Artists", "Playlists")
private val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
private val unselectedIcons =
    listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.StarBorder)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavGraph(
    onBack: () -> Unit,
) {
    val nestedBottomNavController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            StUiTopAppBar(
                onBack = onBack,
                title = "Bottom Navigation",
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(icon = {
                        Icon(
                            if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                            contentDescription = textItems[index],
                        )
                    },
                        label = { Text(textItems[index]) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            nestedBottomNavController.navigate(items[index].route)
                        })
                }
            }
        }) {
        StUiNavHost(
            navController = nestedBottomNavController,
            startDestination = BottomNavScreen.Home.route,
            transitionType = TransitionType.Default,
        ) {
            composable(BottomNavScreen.Home.route) { BottomNavContent(textItems[selectedItem]) }
            composable(BottomNavScreen.Search.route) { BottomNavContent(textItems[selectedItem]) }
            composable(BottomNavScreen.Profile.route) { BottomNavContent(textItems[selectedItem]) }
        }
    }
}

@Composable
private fun BottomNavContent(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title)
    }
}