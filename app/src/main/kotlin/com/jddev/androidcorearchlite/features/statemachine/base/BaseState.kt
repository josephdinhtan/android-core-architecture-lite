package com.jddev.androidcorearchlite.features.statemachine.base

interface BaseState<S> {
    fun handleEvent(event: BaseEvent): S
    fun onEnter()
    fun onExit()
}