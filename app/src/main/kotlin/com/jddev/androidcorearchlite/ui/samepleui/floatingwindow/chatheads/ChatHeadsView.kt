package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.jddev.simpletouch.ui.theme.StUiTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class ChatHeadsView(
    private val context: Context,
    private val screenWidth: StateFlow<Int>,
    private val isThemeModeDark: StateFlow<Boolean>,
    private val lifecycleOwner: LifecycleOwner,
    private val savedStateRegistryOwner: SavedStateRegistryOwner?
) {
    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private var layoutParams: WindowManager.LayoutParams? = null
    private var overlayView: View? = null

    private val _isHolding = MutableStateFlow(false)
    private val isHolding = _isHolding.asStateFlow()

    init {
        initializeLayoutParams()
    }

    fun show() {
        overlayView = ComposeView(context).apply {
            setViewTreeLifecycleOwner(lifecycleOwner)
            setViewTreeSavedStateRegistryOwner(savedStateRegistryOwner)
            setContent {
                val darkTheme = isThemeModeDark.collectAsState()
                StUiTheme(
                    isDarkTheme = darkTheme.value
                ) {
                    val isPressed = isHolding.collectAsState()
                    ChatHeadsViewContent(
                        isPressed = isPressed.value
                    )
                }
            }
        }
        setupView()
        moveToEdge(true)
    }

    fun hide() {
        if (overlayView == null) {
            return
        }
        windowManager.removeView(overlayView)
        overlayView = null
    }

    private fun initializeLayoutParams() {
        layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )

        layoutParams?.gravity = Gravity.TOP or Gravity.START
        layoutParams?.x = 200
        layoutParams?.y = 200
    }


    private fun setupView() {
        windowManager.addView(overlayView, layoutParams)
        try {
            overlayView?.setOnTouchListener(object : OnTouchListener {
                private var initialX = 0
                private var initialY = 0
                private var initialTouchX = 0f
                private var initialTouchY = 0f

                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View, motionEvent: MotionEvent): Boolean {
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            Timber.d("onTouch ACTION_DOWN")
                            _isHolding.tryEmit(true)
                            _isHolding.value = true
                            initialX = layoutParams!!.x
                            initialY = layoutParams!!.y
                            initialTouchX = motionEvent.rawX
                            initialTouchY = motionEvent.rawY

                            animateScale(v, 1.0f, 0.8f)
                            return true
                        }

                        MotionEvent.ACTION_UP -> {
                            Timber.d("onTouch ACTION_UP")
                            _isHolding.tryEmit(false)
                            animateScale(v, 0.8f, 1.0f)
                            moveToEdge()
                            return true
                        }

                        MotionEvent.ACTION_CANCEL -> {
                            Timber.d("onTouch ACTION_CANCEL")
                            _isHolding.tryEmit(false)
                            animateScale(v, 0.8f, 1.0f)
                            moveToEdge()
                            return true
                        }

                        MotionEvent.ACTION_MOVE -> {
//                            layoutParams!!.x = (initialX + motionEvent.rawX - initialTouchX).toInt()
//                            layoutParams!!.y = (initialY + motionEvent.rawY - initialTouchY).toInt()
                            layoutParams!!.x = (motionEvent.rawX - v.width / 2).toInt()
                            layoutParams!!.y = (motionEvent.rawY - v.height / 2).toInt()
                            windowManager.updateViewLayout(v, layoutParams)
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

    private fun moveToEdge(forceMoveToRight: Boolean = false) {
        val startX = layoutParams?.x ?: 0
        val endX = overlayView?.let {
            if (forceMoveToRight) screenWidth.value - it.width
            else if (startX > screenWidth.value / 2) screenWidth.value - it.width
            else 0
        } ?: 0 // Move to the edge of the screen

        val animator = ValueAnimator.ofInt(startX, endX)

        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            layoutParams?.x = animatedValue
            overlayView?.let { windowManager.updateViewLayout(it, layoutParams) }
        }

        animator.duration = 500
        animator.interpolator = OvershootInterpolator() // Add bounce effect
        animator.start()
    }

    private fun animateScale(view: View, startScale: Float, endScale: Float) {
        val animator = ValueAnimator.ofFloat(startScale, endScale)
        animator.duration = 100
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            view.scaleX = animatedValue
            view.scaleY = animatedValue
        }
        animator.start()
    }
}