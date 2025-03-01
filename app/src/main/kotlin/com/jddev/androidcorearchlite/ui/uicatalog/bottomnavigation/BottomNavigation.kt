package com.jddev.androidcorearchlite.ui.uicatalog.bottomnavigation


import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jddev.simpletouch.ui.navigation.StUiBottomNavigation

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination
    val topLevelDestination = NavTopLevelDestination.fromNavDestination(destination)

    StUiBottomNavigation(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        navigationItems = {
            NavTopLevelDestination.entries.forEach {
                val isSelected = it == topLevelDestination
                item(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(it.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = it.imageVector,
                            //contentDescription = stringResource(it.label),
                            contentDescription = it.label,
                        )
                    },
//                    label = {
//                        Text(
//                            text = stringResource(it.label),
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                    },
                    alwaysShowLabel = false,
                )
            }
        },
    ) {
        content()
    }
}