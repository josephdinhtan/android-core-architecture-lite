package com.jddev.androidcorearchlite.features.statemachine.water.states

import com.jddev.androidcorearchlite.features.statemachine.base.BaseEvent
import com.jddev.androidcorearchlite.features.statemachine.water.events.WaterEvent
import com.jddev.androidcorearchlite.features.statemachine.water.tools.ToolProvider
import timber.log.Timber

class Liquid(private val toolProvider: ToolProvider) : BaseWaterState {
    override fun handleEvent(event: BaseEvent): BaseWaterState {
        Timber.d("Handling event: ${event.javaClass.simpleName}")
        return when (event) {
            is WaterEvent.StartHeating -> {
                toolProvider.kettle.boilWater()
                this
            }
            is WaterEvent.StartCooling -> {
                toolProvider.fridge.coolWatter()
                this
            }
            is WaterEvent.FinishedHeating -> Gas(toolProvider)
            is WaterEvent.FinishedCooling -> Ice(toolProvider)
            else -> {
                Timber.d("Event not effecting Liquid state")
                this
            }
        }
    }

    override fun onEnter() {
        Timber.d("Entering Liquid State")
    }

    override fun onExit() {
        Timber.d("Exiting Liquid State")
    }
}