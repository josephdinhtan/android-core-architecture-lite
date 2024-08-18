package com.jddev.androidcorearchlite.features.statemachine.water.tools

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class Fridge(
    private val eventFlow: MutableSharedFlow<FridgeEvent>,
    private val coroutineScope: CoroutineScope
) {
    fun coolWatter() {
        coroutineScope.launch {
            eventFlow.emit(FridgeEvent.StartCooling)
            // Simulate cooling process
            delay(1000)
            eventFlow.emit(FridgeEvent.CoolingFinished)
        }
    }
}

sealed class FridgeEvent {
    data object StartCooling : FridgeEvent()
    data object CoolingFinished : FridgeEvent()
}