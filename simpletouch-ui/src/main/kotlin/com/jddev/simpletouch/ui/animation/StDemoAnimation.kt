package com.jddev.simpletouch.ui.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

class MorphPolygonShape(
    private val morph: Morph, private val percentage: Float
) : Shape {

    private val matrix = Matrix()
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        // Below assumes that you haven't changed the default radius of 1f, nor the centerX and centerY of 0f
        // By default this stretches the path to the size of the container, if you don't want stretching, use the same size.width for both x and y.
        matrix.scale(size.width / 2f, size.height / 2f)
        matrix.translate(1f, 1f)

        val path = morph.toPath(progress = percentage).asComposePath()
        path.transform(matrix)
        return Outline.Generic(path)
    }
}

@Composable
private fun ClickShapeAnimation() {
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
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium)
    )
    Box(modifier = Modifier
        .size(200.dp)
        .padding(8.dp)
        .clip(MorphPolygonShape(morph, animatedProgress.value))
        .background(Color(0xFF80DEEA))
        .size(200.dp)
        .clickable(interactionSource = interactionSource, indication = null) {}) {
        Text("Hello", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ExpandAnimatedContent(
    expanded: Boolean,
    transitionDuration: Int = 1000,
    firstContent: @Composable () -> Unit,
    secondContent: @Composable () -> Unit,
    secondViewDisplayed: (() -> Unit)? = null,
    firstViewDisplayed: (() -> Unit)? = null
) {
//    val trackTransition =
//        updateTransition(targetState = expanded, label = "AnimatedContentTransition")
    AnimatedContent<Boolean>(targetState = expanded, label = "size transform", transitionSpec = {
        ContentTransform(
            targetContentEnter = fadeIn(animationSpec = tween(transitionDuration * 9 / 10)),
            initialContentExit = fadeOut(
                animationSpec = tween(
                    transitionDuration / 2, transitionDuration / 2
                )
            ),
            sizeTransform = SizeTransform(sizeAnimationSpec = { _, _ -> tween(transitionDuration) })
        )
    }
//        transitionSpec = {
//            fadeIn(animationSpec = tween(fadeDuration, fadeDuration)) togetherWith
//                    fadeOut(animationSpec = tween(fadeDuration, delayMillis = 1000)) using
//                    SizeTransform { initialSize, targetSize ->
//                        if (targetState) {
//                            keyframes {
//                                // Expand horizontally first.
//                                //IntSize(targetSize.width, initialSize.height) at 150
//                                durationMillis = transitionDuration
//                            }
//                        } else {
//                            keyframes {
//                                // Shrink vertically first.
////                                IntSize(initialSize.width, targetSize.height) at 150
//                                durationMillis = transitionDuration
//                            }
//                        }
//                    }
//        },
    ) { targetExpanded ->
        if (!targetExpanded) {
            firstContent()
        } else {
            secondContent()
        }
    }
    LaunchedEffect(expanded) {
        kotlinx.coroutines.delay(transitionDuration.toLong())
        if (expanded) {
            secondViewDisplayed?.invoke()
        } else {
            firstViewDisplayed?.invoke()
        }
    }
//    LaunchedEffect(trackTransition.currentState, trackTransition.targetState) {
//        if (!trackTransition.currentState && !trackTransition.targetState) {
//            firstViewDisplayed?.invoke()
//        }
//        if (trackTransition.currentState && trackTransition.targetState) {
//            secondViewDisplayed?.invoke()
//        }
//    }
}

@Composable
@StUiPreview
fun Preview() {
    StUiPreviewWrapper(fullScreen = true) {
        var expanded by remember { mutableStateOf(false) }
        ClickShapeAnimation()
        Button(onClick = { expanded = !expanded }) {
            Text("Expand")
        }
        Box(
            Modifier
                .padding(16.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            ExpandAnimatedContent(expanded = expanded, firstContent = {
                Icon(Icons.Default.FavoriteBorder, null)
            }, secondContent = {
                Text("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
            })
        }
    }
}