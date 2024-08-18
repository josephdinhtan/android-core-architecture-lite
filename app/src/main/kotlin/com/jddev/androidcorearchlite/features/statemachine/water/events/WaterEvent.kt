package com.jddev.androidcorearchlite.features.statemachine.water.events

import com.jddev.androidcorearchlite.features.statemachine.base.BaseEvent

sealed interface WaterEvent : BaseEvent {
    data object StartHeating : WaterEvent
    data object FinishedHeating : WaterEvent
    data object StartCooling : WaterEvent
    data object FinishedCooling : WaterEvent
}