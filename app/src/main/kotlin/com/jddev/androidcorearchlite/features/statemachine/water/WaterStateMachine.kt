package com.jddev.androidcorearchlite.features.statemachine.water

import com.jddev.androidcorearchlite.features.statemachine.base.BaseStateMachine
import com.jddev.androidcorearchlite.features.statemachine.water.events.WaterEvent
import com.jddev.androidcorearchlite.features.statemachine.water.states.BaseWaterState
import timber.log.Timber

class WaterStateMachine(
    initState: BaseWaterState
) : BaseStateMachine<BaseWaterState, WaterEvent>(initState) {

    init {
        Timber.d("WaterStateMachine initialized currentState: ${currentState.value.javaClass.simpleName}")
    }
}