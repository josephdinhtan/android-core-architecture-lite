package com.jddev.androidcorearchlite.ui.samepleui.snakegame

import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold

@Composable
fun SnakeGameRoute(
    onBack: () -> Unit,
) {
    StUiSimpleScaffold(
        title = "Snake Game",
        onBack = onBack
    ) {
        SnakeGameContent()
    }
}