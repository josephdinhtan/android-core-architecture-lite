package com.jddev.androidcorearchlite.ui.statemachinedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jddev.androidcorearchlite.features.statemachine.water.WaterManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StateMachineViewModel(
    private val waterManager: WaterManager
) : ViewModel() {

    private val _oldState = MutableStateFlow<String>("")
    val oldState = _oldState.asStateFlow()

    private val _currentState = MutableStateFlow<String>("")
    val currentState = _currentState.asStateFlow()

    private val _lastEvent = MutableStateFlow<String>("")
    val lastEvent = _lastEvent.asStateFlow()

    init {
        _currentState.value = waterManager.currentState.value::class.simpleName ?: ""
        bindData()
    }

    fun makeHeat() {
        waterManager.makeHeat()
        _lastEvent.value = "Trigger Heat"
    }

    fun makeCool() {
        waterManager.makeCold()
        _lastEvent.value = "Trigger Cold"
    }

    private fun bindData() {
        viewModelScope.launch {
            waterManager.currentState.collect { waterState ->
                _oldState.value = _currentState.value
                _currentState.value = waterState::class.simpleName ?: ""
            }
        }
    }

    companion object {
        fun provideFactory(waterManager: WaterManager) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StateMachineViewModel(waterManager) as T
            }
        }
    }
}