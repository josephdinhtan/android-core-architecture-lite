package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Point
import android.util.Size
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.jddev.simpletouch.ui.theme.StUiTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.abs
import kotlin.math.sqrt

class ChatHeadsView(
    private val context: Context,
    private val screenSize: StateFlow<Size>,
    private val isThemeModeDark: StateFlow<Boolean>,
    private val lifecycleOwner: LifecycleOwner,
    private val savedStateRegistryOwner: SavedStateRegistryOwner?,
    private val command: StateFlow<ChatHeadsCommands>,
) {
    private val _requestShowChatContent = MutableSharedFlow<Unit>(replay = 1)
    val requestShowChatContent = _requestShowChatContent.asSharedFlow()

    private var isViewsInitialized = false

    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private var chatHeadsView: View? = null
    private var chatHeadsLayoutParams: WindowManager.LayoutParams? = null
    private var bottomDeleteView: View? = null
    private var bottomDeleteLayoutParams: WindowManager.LayoutParams? = null

    private val _isDragging = MutableStateFlow(false)
    private val isDragging = _isDragging.asStateFlow()

    private val _showContent = MutableStateFlow(false)
    private val showContent = _showContent.asStateFlow()

    private val _showBottomDeleteView = MutableStateFlow(false)
    private val showBottomDeleteView = _showBottomDeleteView.asStateFlow()
    private val _deleteObjectDetected = MutableStateFlow(false)
    private val deleteObjectDetected = _deleteObjectDetected.asStateFlow()

    private var velocityX: Float = 0f
    private var velocityY: Float = 0f

    private var lastPositionBeforeAnimate: Point = Point(0, 0)
    private var blockPressDuringAnimating = false

    init {
        command.onEach {
            when (it) {
                ChatHeadsCommands.SHOW_CHAT_HEADS -> show()
                ChatHeadsCommands.HIDE_ALL -> hide()
                ChatHeadsCommands.DESTROY_VIEWS -> destroyViews()
                ChatHeadsCommands.HIDE_CHAT_CONTENT -> moveToPreviousPosition()
                else -> {}
            }
        }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
    }

    fun initialize() {
        initializeLayoutParams()
        initializeViews()
        setupView()
        isViewsInitialized = true
    }

    private fun show() {
        if (!isViewsInitialized) throw IllegalAccessException("Views is not initialized")
        _showContent.tryEmit(true)
    }

    private fun hide() {
        Timber.e("Joseph hide chatHeads")
        if (!isViewsInitialized) throw IllegalAccessException("Views is not initialized")
        _showContent.tryEmit(false)
    }

    private fun destroyViews() {
        if (!isViewsInitialized) throw IllegalAccessException("Views is not initialized")
        chatHeadsView?.let { windowManager.removeView(it) }
        bottomDeleteView?.let { windowManager.removeView(it) }
        chatHeadsView = null
        chatHeadsLayoutParams = null
        bottomDeleteView = null
        bottomDeleteLayoutParams = null
    }

    private fun moveToPreviousPosition() {
        if(chatHeadsView != null && chatHeadsLayoutParams != null) {
            animateToPosition(
                chatHeadsView!!,
                chatHeadsLayoutParams!!.x,
                chatHeadsLayoutParams!!.y,
                lastPositionBeforeAnimate.x,
                lastPositionBeforeAnimate.y,
                {
                    blockPressDuringAnimating = false
                }
            )
        }
    }

    private fun initializeViews() {
        chatHeadsView = ComposeView(context).apply {
            setViewTreeLifecycleOwner(lifecycleOwner)
            setViewTreeSavedStateRegistryOwner(savedStateRegistryOwner)
            setContent {
                val screenSize by screenSize.collectAsState()
                StUiTheme(
                    isDarkTheme = isThemeModeDark.collectAsState().value
                ) {
                    ChatHeadsViewBubbleContent(
                        showContent = showContent.collectAsState().value
                    )
                }
            }
        }

        bottomDeleteView = ComposeView(context).apply {
            setViewTreeLifecycleOwner(lifecycleOwner)
            setViewTreeSavedStateRegistryOwner(savedStateRegistryOwner)
            setContent {
                val darkTheme by isThemeModeDark.collectAsState()
                val screenSize by screenSize.collectAsState()
                val showBottomDeleteView by showBottomDeleteView.collectAsState()
                val deleteObjectDetected by deleteObjectDetected.collectAsState()
                StUiTheme(
                    isDarkTheme = darkTheme
                ) {
                    ChatHeadsDeleteViewContent(
                        Modifier
                            .fillMaxWidth()
                            .height(with(LocalDensity.current) { (screenSize.height / 3).toDp() }),
                        showButton = showBottomDeleteView,
                        objectDetected = deleteObjectDetected,
                    )
                }
            }
        }

        // Add views, which one is added later will be on top
        windowManager.addView(bottomDeleteView, bottomDeleteLayoutParams)
        windowManager.addView(chatHeadsView, chatHeadsLayoutParams)
    }

    private fun showBottomDeleteView() {
        _showBottomDeleteView.tryEmit(true)
    }

    private fun hideBottomDeleteView() {
        _showBottomDeleteView.tryEmit(false)
    }

    private fun initializeLayoutParams() {
        // Chat heads
        chatHeadsLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )
        chatHeadsLayoutParams?.gravity = Gravity.START or Gravity.TOP

        // Background Filter
        bottomDeleteLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )
        bottomDeleteLayoutParams?.gravity = Gravity.BOTTOM or Gravity.START
    }


    private fun setupView() {
        try {
            chatHeadsView?.setOnTouchListener(object : OnTouchListener {
                var initialX = 0
                var initialY = 0
                var initialTouchX = 0f
                var initialTouchY = 0f

                var lastX = 0f
                var lastY = 0f
                var lastTouchTime = 0L

                var isClickDetector = false
                var firstTouchTime = 0L // Track when the user starts touching the view
                val moveThreshold = 10f // The threshold to determine if it's a click
                val timeThreshold = 500L // Time threshold (in milliseconds) for click detection

                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View, motionEvent: MotionEvent): Boolean {
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            Timber.d("onTouch ACTION_DOWN")
                            _isDragging.tryEmit(true)
                            isClickDetector = true

                            initialX = chatHeadsLayoutParams!!.x
                            initialY = chatHeadsLayoutParams!!.y
                            initialTouchX = motionEvent.rawX
                            initialTouchY = motionEvent.rawY

                            lastX = motionEvent.rawX
                            lastY = motionEvent.rawY
                            lastTouchTime = System.currentTimeMillis()
                            firstTouchTime = System.currentTimeMillis()
                            // chatHeadsAnimateScale(v, 1.0f, 0.8f)
                            showBottomDeleteView()

                            return true
                        }

                        MotionEvent.ACTION_MOVE -> {
                            val dx = motionEvent.rawX - lastX
                            val dy = motionEvent.rawY - lastY
                            val currentTime = System.currentTimeMillis()
                            val timeDelta = (currentTime - lastTouchTime).toFloat() / 1000
                            if (timeDelta > 0) {
                                // Calculate: velocity = distance / time
                                velocityX = dx / timeDelta
                                velocityY = dy / timeDelta
                            }
                            val distanceMoved = sqrt((dx * dx + dy * dy).toDouble()).toFloat()
                            // Check if the movement exceeds the threshold
                            if (distanceMoved > moveThreshold) {
                                isClickDetector = false
                            }

                            chatHeadsLayoutParams!!.x =
                                (initialX + motionEvent.rawX - initialTouchX).toInt()
                            chatHeadsLayoutParams!!.y =
                                (initialY + motionEvent.rawY - initialTouchY).toInt()

                            windowManager.updateViewLayout(v, chatHeadsLayoutParams)

                            // Save the last position and time for the next move event
                            lastX = motionEvent.rawX
                            lastY = motionEvent.rawY
                            lastTouchTime = currentTime

                            // show red color when object delete detected
                            chatHeadsLayoutParams?.let {
                                val deleteButtonPosition = getDeleteButtonPosition()
                                if (abs((it.x - deleteButtonPosition.x + v.width / 2).toDouble()) <= screenSize.value.width / 10 && abs(
                                        (it.y - deleteButtonPosition.y + v.height / 2).toDouble()
                                    ) <= screenSize.value.width / 10
                                ) {
                                    _deleteObjectDetected.tryEmit(true)
                                } else {
                                    _deleteObjectDetected.tryEmit(false)
                                }
                            }
                            return true
                        }

                        MotionEvent.ACTION_UP -> {
                            Timber.d("onTouch ACTION_UP")
                            _isDragging.tryEmit(false)
                            // chatHeadsAnimateScale(v, 0.8f, 1.0f)
                            // moveToEdge()
                            startDeceleration(v)

                            // Check if the user's touch was a click
                            val touchDuration = System.currentTimeMillis() - firstTouchTime
                            if (isClickDetector && touchDuration < timeThreshold && !blockPressDuringAnimating) {
                                Timber.d("onClick detected")
                                chatHeadsLayoutParams?.let { params ->
                                    lastPositionBeforeAnimate = Point(params.x, params.y)
//                                    blockPressDuringAnimating = true
                                    animateToPosition(
                                        v,
                                        params.x,
                                        params.y,
                                        screenSize.value.width / 2 - v.width/2,
                                        0,
                                    ) {
                                        _requestShowChatContent.tryEmit(Unit)
                                    }
                                }
                            }
                            hideBottomDeleteView()

                            // show red color when object delete detected
                            chatHeadsLayoutParams?.let {
                                val deleteButtonPosition = getDeleteButtonPosition()
                                if (abs((it.x - deleteButtonPosition.x + v.width / 2).toDouble()) <= screenSize.value.width / 10 && abs(
                                        (it.y - deleteButtonPosition.y + v.height / 2).toDouble()
                                    ) <= screenSize.value.width / 10
                                ) {
                                    _deleteObjectDetected.tryEmit(true)
                                    destroyViews()
                                } else {
                                    _deleteObjectDetected.tryEmit(false)
                                }
                            }
                            return true
                        }

                        MotionEvent.ACTION_CANCEL -> {
                            Timber.d("onTouch ACTION_CANCEL")
                            _isDragging.tryEmit(false)
                            // chatHeadsAnimateScale(v, 0.8f, 1.0f)
                            //moveToEdge()
                            hideBottomDeleteView()
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

    private fun getDeleteButtonPosition(): Point {
        // need sync this position with DeleteButtonView
        return Point(screenSize.value.width / 2, screenSize.value.height / 6 * 5)
    }

    private fun animateToPosition(
        v: View,
        startXin: Int,
        startYin: Int,
        targetX: Int,
        targetY: Int,
        onMoveFinished: () -> Unit
    ) {
        val startX = startXin
        val startY = startYin

        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 400 // Animation duration in ms
            interpolator = OvershootInterpolator(1.0f) // Controls overshoot intensity

            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                chatHeadsLayoutParams?.let { params ->
                    params.x = (startX + (targetX - startX) * progress).toInt()
                    params.y = (startY + (targetY - startY) * progress).toInt()
                    windowManager.updateViewLayout(v, params)
                }
            }

            // Call the callback when animation ends
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    onMoveFinished()
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            start()
        }
    }

    /**
     * Starts deceleration animation after the view is released.
     *
     * @param v The view that is being dragged.
     * @param friction The friction coefficient for deceleration.
     * Reduce the friction to make deceleration last longer
     * @param decayFactor Exponential decay factor (apply slower deceleration).
     */
    private fun startDeceleration(
        v: View,
        friction: Float = 0.015f,
        decayFactor: Float = 0.95f,
    ) {
        velocityX *= friction
        velocityY *= friction
        lifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            while (!isDragging.value) {
                // Apply friction to the velocity
                velocityX *= decayFactor
                velocityY *= decayFactor

                // Update position based on current velocity
                chatHeadsLayoutParams?.let {
                    it.x += velocityX.toInt()
                    it.y += velocityY.toInt()
                    windowManager.updateViewLayout(v, it)
                }

                // If the velocity is small enough, stop decelerating
                if (abs(velocityX) <= 1f && abs(velocityY) <= 1f) {
                    break
                } else {
                    delay(16) // Simulate 60 FPS update
                }
            }
        }
    }

    /**
     * Moves the view to the edge of the screen.
     */
    private fun moveToEdge(forceMoveToRight: Boolean = false) {
        val startX = chatHeadsLayoutParams?.x ?: 0
        val endX = chatHeadsView?.let {
            if (forceMoveToRight) screenSize.value.width - it.width
            else if (startX > screenSize.value.width / 2) screenSize.value.width - it.width
            else 0
        } ?: 0 // Move to the edge of the screen

        val animator = ValueAnimator.ofInt(startX, endX)

        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            chatHeadsLayoutParams?.x = animatedValue
            chatHeadsView?.let { windowManager.updateViewLayout(it, chatHeadsLayoutParams) }
        }

        animator.duration = 500
        animator.interpolator = OvershootInterpolator() // Add bounce effect
        animator.start()
    }

    private fun chatHeadsAnimateScale(view: View, startScale: Float, endScale: Float) {
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