package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.jddev.simpletouch.ui.animation.MorphPolygonShape
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun ChatHeadsViewBubbleContent(
    showContent: Boolean
) {
    if(showContent) {
        ClickShapeAnimation2(true, true)
    }
}

@Composable
private fun ClickShapeAnimation2(
    isPressed: Boolean,
    rotate: Boolean = false,
    text: String = "Hi"
) {
    val shapeA = remember {
        RoundedPolygon(
            6, rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            6, rounding = CornerRounding(0.1f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    //val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium)
    )
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 359f,
        animationSpec = infiniteRepeatable(
            animation = tween<Float>(
                durationMillis = 3000,
                easing = LinearEasing,
            ),
        )
    )
    Box(
        modifier = Modifier.rotate(if (rotate) rotation else 0f)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
                .clip(MorphPolygonShape(morph, animatedProgress.value))
                .background(Color(0xFF80DEEA))
        ) {
            Text(
                text, modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    var click1 by remember { mutableStateOf(false) }
    StUiPreviewWrapper(fullScreen = true) {
        Button(onClick = { click1 = !click1 }) { Text("Click1") }
        ChatHeadsViewBubbleContent(click1)
        Button(onClick = { click1 = !click1 }) { Text("Click1") }
    }
}