package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.jddev.simpletouch.ui.animation.ExpandAnimatedContent
import com.jddev.simpletouch.ui.animation.MorphPolygonShape
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun ChatHeadsViewContent(
    showContent: Boolean,
    exitFullScreen: (() -> Unit)? = null
) {
    var modifier by remember { mutableStateOf(Modifier.padding(0.dp)) }
    LaunchedEffect(showContent) {
        if (showContent)
            modifier = Modifier.fillMaxSize()
    }
    var textDebug by remember { mutableStateOf("") }
    Box(modifier = modifier) {
        ExpandAnimatedContent(
            showContent,
//            transitionDuration = 700,
            firstContent = {
                ClickShapeAnimation2(false, textDebug)
            },
            secondContent = {
                Column {
                    ClickShapeAnimation2(false, textDebug)
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("$textDebug Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                    }
                }
            },
            firstViewDisplayed = {
                textDebug = "1 Showed"
                modifier = Modifier.padding(0.dp)
                exitFullScreen?.invoke()
            },
            secondViewDisplayed = {
                textDebug = "2 Showed"
//                modifier = Modifier.fillMaxSize()
            },
        )
    }
}

@Composable
private fun ClickShapeAnimation2(
    isPressed: Boolean,
    text: String = "Hello"
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
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .background(Color(0xFF80DEEA))
    ) {
        Text(
            if (isPressed) "$text 2" else text, modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@StUiPreview
private fun Preview() {
    var click1 by remember { mutableStateOf(false) }
    StUiPreviewWrapper(fullScreen = true) {
        Button(onClick = { click1 = !click1 }) { Text("Click1") }
        ChatHeadsViewContent(click1)
        Button(onClick = { click1 = !click1 }) { Text("Click1") }
    }
}