package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FloatingWindowViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val _isFloatingViewEnabled = MutableStateFlow(false)
    val isFloatingViewEnabled = _isFloatingViewEnabled.asStateFlow()

    fun floatingViewEnabledStateChanged(enabled: Boolean) {
        if (enabled) {
            FloatingWindowService.showOverlay(context)
        } else {
            FloatingWindowService.hideOverlay(context)
        }
        _isFloatingViewEnabled.tryEmit(enabled)
    }
}