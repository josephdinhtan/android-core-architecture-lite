package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.os.Binder
import android.os.IBinder
import android.util.Size
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.coroutineScope
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import com.jddev.androidcorearchlite.R
import com.jddev.androidcorearchlite.ui.MainActivity
import com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads.ChatHeadsController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FloatingWindowService : Service(), LifecycleOwner, SavedStateRegistryOwner {

    private val _lifecycleRegistry = LifecycleRegistry(this)
    private val _savedStateRegistryController = SavedStateRegistryController.create(this)
    override val savedStateRegistry: SavedStateRegistry =
        _savedStateRegistryController.savedStateRegistry
    override val lifecycle: Lifecycle = _lifecycleRegistry
    private val isThemeModeDark = MutableStateFlow<Boolean>(false)
    private val screenSize = MutableStateFlow<Size>(Size(0, 0))

    private var chatHeadsController: ChatHeadsController? = null

    private val _isServiceRunning = MutableStateFlow(false)
    var isChatHeadsShowing = _isServiceRunning.asStateFlow()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val notification = getNotification()
        startForeground(1, notification)
        _savedStateRegistryController.performAttach()
        _savedStateRegistryController.performRestore(null)
        _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        screenSize.value = Size(
            resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels
        )

        chatHeadsController = ChatHeadsController(
            this, screenSize.asStateFlow(), isThemeModeDark, this, this
        )
        chatHeadsController?.initialize()

        chatHeadsController!!.isShowing.onEach {
            _isServiceRunning.tryEmit(it)
        }.launchIn(lifecycle.coroutineScope)
    }

    inner class LocalBinder : Binder() {
        fun getIsShowingState(): StateFlow<Boolean> = this@FloatingWindowService.isChatHeadsShowing
        fun showChatHeads() {
            _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
            chatHeadsController?.show()
        }

        fun hideChatHeads() {
            chatHeadsController?.hide()
//            _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//            _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        }

        fun stopService() {
            this@FloatingWindowService.stopForeground(STOP_FOREGROUND_REMOVE)
        }
    }

    private val localBinder = LocalBinder()
    override fun onBind(intent: Intent?): IBinder {
        return localBinder
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID, "Core Arch Floating Service Channel", NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        return START_NOT_STICKY
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        isThemeModeDark.value =
            newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        screenSize.value = Size(
            resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        chatHeadsController?.destroyViews()
        chatHeadsController = null
        _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    private fun getNotification(): Notification {
        val notificationIntent = Intent(
            Intent.ACTION_VIEW,
            "https://jddev.com/floating_window/".toUri(),
            this,
            MainActivity::class.java
        )
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("CoreArch")
            .setContentText("Floating window is showing")
            .setSmallIcon(R.drawable.ic_launcher_foreground).setContentIntent(pendingIntent).build()
    }

    companion object {
        private const val CHANNEL_ID = "CoreArchFloatingServiceChannel"
    }
}