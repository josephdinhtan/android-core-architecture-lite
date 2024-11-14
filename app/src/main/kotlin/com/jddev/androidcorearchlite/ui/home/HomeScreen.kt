package com.jddev.androidcorearchlite.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jddev.simpletouch.ui.component.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.theme.StUiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToSettings: () -> Unit,
    navigateToStateMachineDemo: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            StUiLargeTopAppBar(
                modifier = modifier,
                title = "Home",
                actions = {
                    IconButton(onClick = navigateToSettings) {
                        Icon(Icons.Default.Settings, "Settings")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(onClick = navigateToStateMachineDemo) {
                Text(
                    text = "State Machine Demo",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    StUiTheme {
        HomeScreen(
            modifier = Modifier,
            navigateToSettings = {},
            navigateToStateMachineDemo = {},
        )
    }
}