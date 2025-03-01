package com.jddev.androidcorearchlite.ui.uicatalog.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import kotlinx.serialization.Serializable

sealed interface NavRoute {
    @Serializable
    data object Home: NavRoute

    @Serializable
    data object Search: NavRoute

    @Serializable
    data object Settings: NavRoute

    @Serializable
    data object LibraryHome: NavRoute

    @Serializable
    data class LibraryAlbum(val albumId: Long): NavRoute

    @Serializable
    data class LibraryArtist(val artistId: Long): NavRoute
}

enum class NavTopLevelDestination(
    val route: NavRoute,
    //@StringRes val label: Int,
    val label: String,
    val imageVector: ImageVector,
) {
    Timeline(
        route = NavRoute.LibraryHome,
        label = "Library",
        imageVector = Icons.Filled.LibraryMusic,
    ),
    ChatsList(
        route = NavRoute.Search,
        label = "Search",
        imageVector = Icons.Filled.Search,
    ),
    Settings(
        route = NavRoute.Settings,
        label = "Settings",
        imageVector = Icons.Filled.Settings,
    ),
    ;

    companion object {
        val START_DESTINATION = ChatsList

        fun fromNavDestination(destination: NavDestination?): NavTopLevelDestination {
            return entries.find { dest ->
                destination?.hierarchy?.any {
                    it.hasRoute(dest.route::class)
                } == true
            } ?: START_DESTINATION
        }

        fun NavDestination.isTopLevel(): Boolean {
            return entries.any {
                hasRoute(it.route::class)
            }
        }
    }
}