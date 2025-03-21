package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FloatingChatViewContent(
    showContent: Boolean,
    durationMillis: Int = 300,
    onDismissAnimationFinished: () -> Unit
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    var showContentAnimate by remember { mutableStateOf(false) }
    var animating by remember { mutableStateOf(false) }
    LaunchedEffect(showContent) {
        if (showContent) {
            animating = true
            showContentAnimate = true
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = showContentAnimate,
            enter = fadeIn(
                animationSpec = tween(durationMillis)
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1.5f)
                    .clickable {
                        if(animating) return@clickable
                        showContentAnimate = false
                        coroutineScope.launch {
                            delay(durationMillis.toLong())
                            onDismissAnimationFinished()
                        }
                    }
                    .indication(interactionSource = remember { MutableInteractionSource() }, indication = null)
                    .background(color = Color.Black.copy(alpha = 0.4f))
            )

            LaunchedEffect(showContentAnimate) {
                delay(durationMillis.toLong())
                animating = false
            }
        }
        AnimatedVisibility(
            visible = showContentAnimate,
            enter = slideInVertically(
                animationSpec = tween(durationMillis),
                initialOffsetY = {
                    -it / 3
                }
            ) + fadeIn(
                //            initialAlpha = 0.3f,
                animationSpec = tween(durationMillis / 2)
            ) + scaleIn(
                animationSpec = tween(durationMillis),
                initialScale = 0.1f
            ),
            exit = slideOutVertically(
                animationSpec = tween(durationMillis),
                targetOffsetY = {
                    -it / 3
                }
            ) + fadeOut(
                animationSpec = tween(durationMillis / 2, delayMillis = durationMillis / 3)
            ) + scaleOut(
                animationSpec = tween(durationMillis),
                targetScale = 0.1f
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Chat Panel Content")
                        Text("showContent: $showContent")
                    }
                }
            }
        }
    }
}