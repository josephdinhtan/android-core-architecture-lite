package com.jddev.androidcorearchlite.ui.statemachinedemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiScrollBehavior
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.theme.StUiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateMachineScreen(
    modifier: Modifier = Modifier,
    oldState: String,
    currentState: String,
    lastEvent: String,
    makeHeat: () -> Unit,
    makeCool: () -> Unit,
    onBack: () -> Unit,
) {
    val scrollBehavior = StUiScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            StUiTopAppBar(
                modifier = modifier,
                scrollBehavior = scrollBehavior,
                onBack = onBack,
                title = "State Machine",
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = "Water State", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "New State: $currentState", style = MaterialTheme.typography.titleLarge)
                Text(text = "Old State: $oldState")
                Text(text = "Last Event: $lastEvent")
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    ElevatedButton(onClick = makeHeat) {
                        Text("Trigger Heat")
                    }

                    ElevatedButton(onClick = makeCool) {
                        Text("Trigger Cool")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    StUiTheme {
        StateMachineScreen(
            currentState = "NewState",
            oldState = "Ice",
            lastEvent = "Event",
            makeHeat = {},
            makeCool = {},
            onBack = {},
        )
    }
}