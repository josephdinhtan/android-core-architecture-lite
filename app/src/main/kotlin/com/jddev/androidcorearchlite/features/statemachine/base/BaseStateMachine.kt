package com.jddev.androidcorearchlite.features.statemachine.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseStateMachine<S : BaseState<S>, E : BaseEvent>(initialState: S) {
    private val _currentState = MutableStateFlow(initialState)
    val currentState = _currentState.asStateFlow()

    open fun sendEvent(event: E) {
        val newState = _currentState.value.handleEvent(event)
        transitionTo(newState)
    }

    private fun transitionTo(newState: S) {
        val isStateChange = newState != _currentState.value
        if (isStateChange) _currentState.value.onExit()
        _currentState.value = newState
        if (isStateChange) _currentState.value.onEnter()
    }
}