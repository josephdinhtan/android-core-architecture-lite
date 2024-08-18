package com.jddev.androidcorearchlite.ui.statemachinedemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun StateMachineRoute(
    stateMachineViewModel: StateMachineViewModel,
    onBack: () -> Unit,
) {
    val oldState = stateMachineViewModel.oldState.collectAsState()
    val currentState = stateMachineViewModel.currentState.collectAsState()
    val lastEvent = stateMachineViewModel.lastEvent.collectAsState()

    StateMachineScreen(
        oldState = oldState.value,
        currentState = currentState.value,
        lastEvent = lastEvent.value,
        makeHeat = { stateMachineViewModel.makeHeat() },
        makeCool = { stateMachineViewModel.makeCool() },
        onBack = onBack,
    )
}
