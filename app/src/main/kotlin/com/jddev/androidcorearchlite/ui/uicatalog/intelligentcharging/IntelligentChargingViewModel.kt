package com.jddev.androidcorearchlite.ui.uicatalog.intelligentcharging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jddev.androidcorearchlite.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntelligentChargingViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private val _chargeUpToCapacityChecked = MutableStateFlow(false)
    val chargeUpToCapacityChecked = _chargeUpToCapacityChecked.asStateFlow()

    private val _chargeOnlyDisplayChecked = MutableStateFlow(false)
    val chargeOnlyDisplayChecked = _chargeOnlyDisplayChecked.asStateFlow()

    init {
        viewModelScope.launch {
            chargeUpToCapacityChecked.collect { checked ->
                // Show notification
                if (checked)
                    notificationRepository.showChargeLimitNotification()
                else
                    notificationRepository.cancelChargeLimitNotification()
            }
        }
        viewModelScope.launch {
            chargeOnlyDisplayChecked.collect {

            }
        }
    }

    fun chargeUpToCapacityChange(checked: Boolean) {
        // do something here
        // Send to framework or hardware
        _chargeUpToCapacityChecked.tryEmit(checked)
    }

    fun chargeOnlyDisplayChange(checked: Boolean) {
        // do something here
        _chargeOnlyDisplayChecked.tryEmit(checked)
    }
}