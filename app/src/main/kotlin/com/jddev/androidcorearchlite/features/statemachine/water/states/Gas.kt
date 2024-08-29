package com.jddev.androidcorearchlite.features.statemachine.water.states

import com.jddev.androidcorearchlite.features.statemachine.base.BaseEvent
import com.jddev.androidcorearchlite.features.statemachine.water.events.WaterEvent
import com.jddev.androidcorearchlite.features.statemachine.water.tools.ToolProvider
import timber.log.Timber

class Gas(private val toolProvider: ToolProvider) : BaseWaterState {
    override fun handleEvent(event: BaseEvent): BaseWaterState {
        Timber.d("Handling event: ${event.javaClass.simpleName}")
        return when (event) {
            is WaterEvent.StartCooling -> {
                toolProvider.fridge.coolWatter()
                this
            }
            is WaterEvent.FinishedCooling -> Liquid(toolProvider)
            else -> {
                Timber.d("Event not effecting Gas state")
                this
            }
        }
    }

    override fun onEnter() {
        Timber.d("Entering Gas State")
    }

    override fun onExit() {
        Timber.d("Exiting Gas State")
    }
}