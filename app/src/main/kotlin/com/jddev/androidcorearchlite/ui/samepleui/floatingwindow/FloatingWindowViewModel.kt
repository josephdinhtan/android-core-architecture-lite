package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FloatingWindowViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _isFloatingViewEnabled = MutableStateFlow(false)
    val isFloatingViewEnabled = _isFloatingViewEnabled.asStateFlow()

    private val _isServiceRunning = MutableStateFlow(false)
    val isServiceRunning = _isServiceRunning.asStateFlow()

    private var localService: FloatingWindowService.LocalBinder? = null

    fun requestStartStopService(enabled: Boolean) {
        if (enabled) {
            startMonitorState()
        } else {
            context.unbindService(serviceConnection)
            _isServiceRunning.tryEmit(false)
        }
    }

    fun floatingViewEnabledStateChanged(enabled: Boolean) {
        if (enabled) {
            localService?.showChatHeads()
        } else {
            localService?.hideChatHeads()
        }
    }

    private var coroutineJob: Job? = null

    private fun getCoroutineJobViewMonitor() =
        viewModelScope.launch {
            Timber.e("Joseph isShowingState viewModelScope.launch relunch")
            localService?.getIsShowingState()?.collect {
                Timber.e("Joseph isShowingState $it")
                _isFloatingViewEnabled.tryEmit(it)
            }
        }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            localService = (binder as FloatingWindowService.LocalBinder)
            _isServiceRunning.tryEmit(true)

            coroutineJob?.cancel()
            coroutineJob = getCoroutineJobViewMonitor()
            coroutineJob?.start()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            _isServiceRunning.tryEmit(false)
        }
    }

    private fun startMonitorState() {
        val intent = Intent(context, FloatingWindowService::class.java)
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onCleared() {
        super.onCleared()
        if(isServiceRunning.value) {
            context.unbindService(serviceConnection)
        }
    }
}