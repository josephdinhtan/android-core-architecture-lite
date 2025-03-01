package com.jddev.androidcorearchlite.ui.archcatalog.statemachinedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jddev.androidcorearchlite.features.statemachine.water.WaterManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateMachineViewModel @Inject constructor (
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
}