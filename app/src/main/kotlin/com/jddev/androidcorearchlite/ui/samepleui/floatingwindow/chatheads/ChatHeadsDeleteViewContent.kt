package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import kotlinx.coroutines.delay

private const val TIME_TO_DISMISS = 400
private const val SIZE_OF_DELETE_BUTTON = 60

@Composable
fun ChatHeadsDeleteViewContent(
    modifier: Modifier = Modifier,
    showButton: Boolean,
    objectDetected: Boolean = false,
) {
    var showAnimated by remember { mutableStateOf(false) }
    var parentHeight by remember { mutableIntStateOf(0) }

    val density = LocalDensity.current
    LaunchedEffect(showButton) {
        if (showButton) showAnimated = true
        else {
            delay(TIME_TO_DISMISS.toLong())
            showAnimated = false
        }
    }

    if (showAnimated) {
        var animateIn by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) { animateIn = true }
        Box(modifier = modifier
            .onGloballyPositioned { layoutCoordinates ->
                parentHeight = layoutCoordinates.size.height
            }
            .clearAndSetSemantics { }
        ) {
            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxSize().clearAndSetSemantics { },
                visible = animateIn && showButton,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)),
                exit = fadeOut(animationSpec = tween(durationMillis = 150))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.1f),
                                    Color.Black.copy(alpha = 0.2f),
                                )
                            )
                        )
                        .clearAndSetSemantics { }
                )
            }
            AnimatedVisibility(
                modifier = Modifier.clearAndSetSemantics { },
                visible = animateIn && showButton,
                enter = slideInVertically(
                    animationSpec = tween(TIME_TO_DISMISS),
                    initialOffsetY = { with(density) { parentHeight / 2 + SIZE_OF_DELETE_BUTTON.dp.roundToPx() } },
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically(
                    targetOffsetY = { with(density) { parentHeight / 2 + SIZE_OF_DELETE_BUTTON.dp.roundToPx() } },
                    animationSpec = tween(TIME_TO_DISMISS)
                ) + fadeOut(
                    targetAlpha = 0.3f
                ),
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .clearAndSetSemantics { },
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier
                            .size(SIZE_OF_DELETE_BUTTON.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape),
                        color = Color.Black.copy(alpha = 0.3f)
                    ) {
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    AnimatedVisibility(
                        visible = animateIn && showButton && objectDetected,
                        enter = fadeIn(animationSpec = tween(durationMillis = 150)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 150))
                    ) {
                        Box(
                            modifier = Modifier
                                .size((SIZE_OF_DELETE_BUTTON + 4).dp)
                                .clip(CircleShape)
                                .background(Color.Red.copy(alpha = 0.3f)),
                        )
                    }

                    DisposableEffect(Unit) {
                        onDispose {
                            showAnimated = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper(fullScreen = true) {
        var show by remember { mutableStateOf(true) }
        var count by remember { mutableStateOf(0) }
        var deleteDetected by remember { mutableStateOf(false) }
        Button(onClick = { show = !show }) { Text("Show") }
        Button(onClick = { deleteDetected = !deleteDetected }) { Text("Delete detected") }

        Box {
            Column {
                Button(onClick = { count++ }) { Text("Increment under other Views") }
                Text("Test count: $count")
            }
            ChatHeadsDeleteViewContent(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp), show, deleteDetected
            )
        }
    }
}