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
    SHOW_CHAT_HEADS,
    HIDE_ALL,
    SHOW_FLOATING_CHAT,
    HIDE_FLOATING_CHAT
}

class ChatHeadsController(
    private val context: Context,
    private val screenSize: StateFlow<Size>,
    private val isThemeModeDark: StateFlow<Boolean>,
    private val lifecycleOwner: LifecycleOwner,
    private val savedStateRegistryOwner: SavedStateRegistryOwner?
) {
    private val _command = MutableStateFlow<ChatHeadsCommands>(ChatHeadsCommands.HIDE_ALL)
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
            floatingChatView.show()
        }.launchIn(lifecycleOwner.lifecycle.coroutineScope)

        floatingChatView.requestDismissContent.onEach {
            floatingChatView.hide()
            chatHeadsView.moveToPreviousPosition()
        }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
    }

    fun destroyViews() {
        chatHeadsView.destroyViews()
        floatingChatView.destroyViews()
    }

    fun show() {
        chatHeadsView.show()
    }

    fun hide() {
        chatHeadsView.hide()
    }
}