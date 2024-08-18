package com.jddev.androidcorearchlite.features.statemachine.water.tools

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class Kettle(
    private val eventFlow: MutableSharedFlow<KettleEvent>,
    private val coroutineScope: CoroutineScope
) {
    fun boilWater() {
        coroutineScope.launch {
            eventFlow.emit(KettleEvent.StartHeating)
            // Simulate heating process
            delay(1000)
            eventFlow.emit(KettleEvent.HeatingFinished)
        }
    }
}

sealed class KettleEvent {
    data object StartHeating : KettleEvent()
    data object HeatingFinished : KettleEvent()
}