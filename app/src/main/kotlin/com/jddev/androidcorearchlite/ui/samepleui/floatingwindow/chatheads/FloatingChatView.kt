package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.Size
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.jddev.simpletouch.ui.theme.StUiTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class FloatingChatView(
    private val context: Context,
    private val screenSize: StateFlow<Size>,
    private val isThemeModeDark: StateFlow<Boolean>,
    private val lifecycleOwner: LifecycleOwner,
    private val savedStateRegistryOwner: SavedStateRegistryOwner?,
    private val command: StateFlow<ChatHeadsCommands>,
) {
    private var isViewsInitialized = false

    private val _requestDismissContent = MutableSharedFlow<Unit>(replay = 1)
    val requestDismissContent = _requestDismissContent.asSharedFlow()

    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private var chatPanelView: View? = null
    private var chatPanelLayoutParams: WindowManager.LayoutParams? = null

    private val _showChatPanel = MutableStateFlow(false)
    private val showChatPanel = _showChatPanel.asStateFlow()

    fun initialize() {
        initializeLayoutParams()
        initializeView()
//        addViewsToWindowManager()
        setupView()
        isViewsInitialized = true
    }

    fun show() {
        if (!isViewsInitialized) throw IllegalAccessException("Views is not initialized")
        addViewsToWindowManager()
        _showChatPanel.tryEmit(true)
    }

    fun hide() {
        if (!isViewsInitialized) throw IllegalAccessException("Views is not initialized")
        chatPanelView?.let {
            if (it.isAttachedToWindow) {
                windowManager.removeView(it)
            }
        }
    }

    fun destroyViews() {
        if (!isViewsInitialized) throw IllegalAccessException("Views is not initialized")
        chatPanelView?.let {
            if (it.isAttachedToWindow) {
                windowManager.removeView(it)
            }
        }
        chatPanelView = null
        chatPanelLayoutParams = null
    }

    private fun initializeView() {
        chatPanelView = ComposeView(context).apply {
            setViewTreeLifecycleOwner(lifecycleOwner)
            setViewTreeSavedStateRegistryOwner(savedStateRegistryOwner)
            setContent {
                val darkTheme = isThemeModeDark.collectAsState()
                StUiTheme(
                    isDarkTheme = darkTheme.value
                ) {
                    FloatingChatViewContent(showContent = showChatPanel.collectAsState().value,
                        onDismissAnimationFinished = {
                            _requestDismissContent.tryEmit(Unit)
                        })
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            chatPanelView?.addOnUnhandledKeyEventListener { view, event ->
                if (event.action == KeyEvent.KEYCODE_BACK || event.action == KeyEvent.KEYCODE_SOFT_LEFT || event.action == KeyEvent.KEYCODE_SOFT_RIGHT || event.action == KeyEvent.KEYCODE_HOME || event.action == KeyEvent.KEYCODE_MOVE_HOME) {
                    if (showChatPanel.value) {
                        hide()
                    }
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun addViewsToWindowManager() {
        chatPanelView?.let {
            windowManager.addView(it, chatPanelLayoutParams)
        }
    }

    private fun initializeLayoutParams() {
        // Chat content
        chatPanelLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )
        chatPanelLayoutParams?.gravity = Gravity.START or Gravity.TOP
    }


    private fun setupView() {
        try {
            chatPanelView?.setOnTouchListener(object : OnTouchListener {
                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View, motionEvent: MotionEvent): Boolean {
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            return true
                        }

                        MotionEvent.ACTION_MOVE -> {
                            return true
                        }

                        MotionEvent.ACTION_UP -> {
                            return true
                        }

                        MotionEvent.ACTION_CANCEL -> {
                            return true
                        }
                    }
                    return false
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}