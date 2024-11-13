package com.jddev.androidcorearchlite.features.statemachine.water

import com.jddev.androidcorearchlite.features.statemachine.water.events.WaterEvent
import com.jddev.androidcorearchlite.features.statemachine.water.states.Liquid
import com.jddev.androidcorearchlite.features.statemachine.water.tools.Fridge
import com.jddev.androidcorearchlite.features.statemachine.water.tools.FridgeEvent
import com.jddev.androidcorearchlite.features.statemachine.water.tools.Kettle
import com.jddev.androidcorearchlite.features.statemachine.water.tools.KettleEvent
import com.jddev.androidcorearchlite.features.statemachine.water.tools.ToolProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class WaterManager @Inject constructor() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val fridgeEventFlow = MutableSharedFlow<FridgeEvent>()
    private val kettleEventFlow = MutableSharedFlow<KettleEvent>()

    private val toolProvider: ToolProvider = ToolProvider(
        fridge = Fridge(fridgeEventFlow, coroutineScope),
        kettle = Kettle(kettleEventFlow, coroutineScope)
    )

    private val waterStateMachine: WaterStateMachine = WaterStateMachine(Liquid(toolProvider))

    val currentState get() = waterStateMachine.currentState

    init {
        bindData()
    }

    fun makeHeat() {
        waterStateMachine.sendEvent(WaterEvent.StartHeating)
    }

    fun makeCold() {
        waterStateMachine.sendEvent(WaterEvent.StartCooling)
    }

    private fun bindData() {
        coroutineScope.launch {
            fridgeEventFlow.collect { event ->
                when (event) {
                    is FridgeEvent.CoolingFinished -> waterStateMachine.sendEvent(WaterEvent.FinishedCooling)
                    is FridgeEvent.StartCooling -> {
                        Timber.d("Fridge cooling started")
                    }
                }
            }
        }

        coroutineScope.launch {
            kettleEventFlow.collect { event ->
                when (event) {
                    is KettleEvent.HeatingFinished -> waterStateMachine.sendEvent(WaterEvent.FinishedHeating)
                    is KettleEvent.StartHeating -> {
                        Timber.d("Kettle heating started")
                    }
                }
            }
        }
    }
}