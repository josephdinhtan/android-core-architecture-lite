package com.jddev.simpletouch.ui.component

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlin.math.roundToInt

@Composable
fun StUiDragBox(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.BottomEnd,
    content: @Composable () -> Unit
) {
    var boxSizeLoaded = remember { false }
    var contentSizeLoaded = remember { false }
    var boxSize by remember { mutableStateOf(IntSize.Zero) }
    var contentSize by remember { mutableStateOf(IntSize.Zero) }

    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(modifier = modifier.onGloballyPositioned {
        if (!boxSizeLoaded) {
            boxSize = it.size
            boxSizeLoaded = true
            val (newOffsetX, newOffsetY) = getOffset(alignment, boxSize, contentSize)
            offsetX = newOffsetX
            offsetY = newOffsetY
        }
    }) {
        Box(modifier = Modifier
            .offset {
                IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
            }
            .onGloballyPositioned {
                if (!contentSizeLoaded) {
                    contentSize = it.size
                    contentSizeLoaded = true
                    val (newOffsetX, newOffsetY) = getOffset(alignment, boxSize, contentSize)
                    offsetX = newOffsetX
                    offsetY = newOffsetY
                }
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    // Calculate new position
                    val newX = offsetX + dragAmount.x
                    val newY = offsetY + dragAmount.y

                    // Apply bounds to prevent exceeding screen size
                    offsetX = newX.coerceIn(0f, boxSize.width.toFloat() - contentSize.width)
                    offsetY = newY.coerceIn(0f, boxSize.height.toFloat() - contentSize.height)
                }
            }) {
            content.invoke()
        }
    }
}

private fun getOffset(
    alignment: Alignment, boxSize: IntSize, contentSize: IntSize
): Pair<Float, Float> {
    val offsetX: Float
    val offsetY: Float
    when (alignment) {
        Alignment.BottomEnd -> {
            offsetX = boxSize.width.toFloat() - contentSize.width
            offsetY = boxSize.height.toFloat() - contentSize.height
        }

        Alignment.BottomStart -> {
            offsetX = 0f
            offsetY = boxSize.height.toFloat() - contentSize.height
        }

        Alignment.TopEnd -> {
            offsetX = boxSize.width.toFloat() - contentSize.width
            offsetY = 0f
        }

        Alignment.TopStart -> {
            offsetX = 0f
            offsetY = 0f
        }

        Alignment.Center -> {
            offsetX = (boxSize.width.toFloat() - contentSize.width) / 2
            offsetY = (boxSize.height.toFloat() - contentSize.height) / 2
        }

        Alignment.BottomCenter -> {
            offsetX = (boxSize.width.toFloat() - contentSize.width) / 2
            offsetY = boxSize.height.toFloat() - contentSize.height
        }

        Alignment.TopCenter -> {
            offsetX = (boxSize.width.toFloat() - contentSize.width) / 2
            offsetY = 0f
        }

        Alignment.CenterStart -> {
            offsetX = 0f
            offsetY = (boxSize.height.toFloat() - contentSize.height) / 2
        }

        Alignment.CenterEnd -> {
            offsetX = boxSize.width.toFloat() - contentSize.width
            offsetY = (boxSize.height.toFloat() - contentSize.height) / 2
        }

        else -> {
            offsetX = 0f
            offsetY = 0f
        }
    }
    return offsetX to offsetY
}