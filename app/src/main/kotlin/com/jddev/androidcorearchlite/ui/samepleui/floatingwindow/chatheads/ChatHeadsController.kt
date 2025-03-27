package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads

import android.content.Context
import android.util.Size
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

enum class ChatHeadsCommands {
    NONE,
    SHOW_CHAT_HEADS,
    HIDE_ALL,
    SHOW_CHAT_CONTENT,
    HIDE_CHAT_CONTENT,
    DESTROY_VIEWS,
}

class ChatHeadsController(
    private val context: Context,
    private val screenSize: StateFlow<Size>,
    private val isThemeModeDark: StateFlow<Boolean>,
    private val lifecycleOwner: LifecycleOwner,
    private val savedStateRegistryOwner: SavedStateRegistryOwner?
) {
    private val _isShowing = MutableStateFlow(false)
    val isShowing = _isShowing.asStateFlow()

    private val _command = MutableStateFlow<ChatHeadsCommands>(ChatHeadsCommands.NONE)
    private val command = _command.asStateFlow()

    private val chatHeadsView = ChatHeadsView(
        context = context,
        screenSize = screenSize,
        isThemeModeDark = isThemeModeDark,
        lifecycleOwner = lifecycleOwner,
        savedStateRegistryOwner = savedStateRegistryOwner,
        command = command
    )

    private val floatingChatView = FloatingChatView(
        context = context,
        screenSize = screenSize,
        isThemeModeDark = isThemeModeDark,
        lifecycleOwner = lifecycleOwner,
        savedStateRegistryOwner = savedStateRegistryOwner,
        command = command
    )

    fun initialize() {
        chatHeadsView.initialize()
        floatingChatView.initialize()

        chatHeadsView.requestShowChatContent.onEach {
            _command.tryEmit(ChatHeadsCommands.SHOW_CHAT_CONTENT)
        }.launchIn(lifecycleOwner.lifecycle.coroutineScope)

        floatingChatView.requestDismissContent.onEach {
            _command.tryEmit(ChatHeadsCommands.HIDE_CHAT_CONTENT)
        }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
    }

    fun destroyViews() {
        _command.tryEmit(ChatHeadsCommands.DESTROY_VIEWS)
        _isShowing.tryEmit(false)
    }

    fun show() {
        _command.tryEmit(ChatHeadsCommands.SHOW_CHAT_HEADS)
        _isShowing.tryEmit(true)
    }

    fun hide() {
        _command.tryEmit(ChatHeadsCommands.HIDE_ALL)
        _isShowing.tryEmit(false)
    }
}