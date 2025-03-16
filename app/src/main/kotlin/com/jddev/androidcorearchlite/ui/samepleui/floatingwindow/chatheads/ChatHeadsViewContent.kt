package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow.chatheads


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.jddev.simpletouch.ui.animation.MorphPolygonShape

@Composable
fun ChatHeadsViewContent(
    isPressed: Boolean,
) {
    Box {
        ClickShapeAnimation(isPressed)
    }
}

@Composable
fun ClickShapeAnimation(
    isPressed2: Boolean,
) {
    val shapeA = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            6,
            rounding = CornerRounding(0.1f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
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
//            .clickable(interactionSource = interactionSource, indication = null) {}
    ) {
        Text("Hello", modifier = Modifier.align(Alignment.Center))
    }
}