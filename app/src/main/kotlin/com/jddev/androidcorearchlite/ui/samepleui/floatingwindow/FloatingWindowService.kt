package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import com.jddev.androidcorearchlite.R
import com.jddev.androidcorearchlite.ui.MainActivity
import com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads.ChatHeadsView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FloatingWindowService : Service(), LifecycleOwner, SavedStateRegistryOwner {

    private val _lifecycleRegistry = LifecycleRegistry(this)
    private val _savedStateRegistryController = SavedStateRegistryController.create(this)
    override val savedStateRegistry = _savedStateRegistryController.savedStateRegistry
    override val lifecycle: Lifecycle = _lifecycleRegistry

    private val isThemeModeDark = MutableStateFlow<Boolean>(false)
    private val screenWidth = MutableStateFlow<Int>(0)

    private lateinit var chatHeadsView: ChatHeadsView

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val notification = getNotification()
        startForeground(1, notification)
        _savedStateRegistryController.performAttach()
        _savedStateRegistryController.performRestore(null)
        _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        screenWidth.value = resources.displayMetrics.widthPixels

        chatHeadsView = ChatHeadsView(
            this, screenWidth.asStateFlow(), isThemeModeDark, this, this
        )
    }

    override fun onBind(intent: Intent?): IBinder? {
        throw RuntimeException("bound mode not supported")
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "Core Arch Floating Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent.hasExtra(INTENT_EXTRA_COMMAND_SHOW_OVERLAY)) {
            if (!isShowing) {
                isShowing = true
                chatHeadsView.show()
            }
        }
        if (intent.hasExtra(INTENT_EXTRA_COMMAND_HIDE_OVERLAY)) {
            isShowing = false
            _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            _lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
            chatHeadsView.hide()
            stopSelf()
        }
        return START_NOT_STICKY
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        isThemeModeDark.value =
            newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        screenWidth.value = resources.displayMetrics.widthPixels
    }

    override fun onDestroy() {
        super.onDestroy()
        chatHeadsView.hide()
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
            this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("CoreArch")
            .setContentText("Floating window is showing")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }

    companion object {
        private const val CHANNEL_ID = "CoreArchFloatingServiceChannel"
        private const val INTENT_EXTRA_COMMAND_SHOW_OVERLAY = "INTENT_EXTRA_COMMAND_SHOW_OVERLAY"
        private const val INTENT_EXTRA_COMMAND_HIDE_OVERLAY = "INTENT_EXTRA_COMMAND_HIDE_OVERLAY"
        internal fun showOverlay(context: Context) {
            val intent = Intent(context, FloatingWindowService::class.java)
            intent.putExtra(INTENT_EXTRA_COMMAND_SHOW_OVERLAY, true)
            context.applicationContext.startService(intent)
        }

        internal fun hideOverlay(context: Context) {
            val intent = Intent(context, FloatingWindowService::class.java)
            intent.putExtra(INTENT_EXTRA_COMMAND_HIDE_OVERLAY, true)
            context.applicationContext.startService(intent)
        }

        // TODO: just for demo only, this is causing of memory leak problem
        internal var isShowing = false
    }
}