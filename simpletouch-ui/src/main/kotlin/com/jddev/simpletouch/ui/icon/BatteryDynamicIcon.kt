package com.jddev.simpletouch.ui.icon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

private val WIDTH = 50.dp
private val HEIGHT = 25.dp

@Composable
fun BatteryDynamicIcon(
    modifier: Modifier = Modifier,
    batteryPercentage: Int,
    verticalShape: Boolean = false,
    showPercentage: Boolean = false,
    indicationColor: Color = if (batteryPercentage > 20) Color.Green else Color.Red,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface
) {
    var battPercentage = batteryPercentage
    if (batteryPercentage < 0) battPercentage = 0
    if (batteryPercentage > 100) battPercentage = 100
    if (verticalShape) {
        VerticalDynamicBatteryIcon(
            modifier = modifier,
            batteryPercentage = battPercentage,
            showPercentage = showPercentage,
            indicationColor = indicationColor,
            backgroundColor = backgroundColor
        )
    } else {
        HorizontalDynamicBatteryIcon(
            modifier = modifier,
            batteryPercentage = battPercentage,
            showPercentage = showPercentage,
            indicationColor = indicationColor,
            backgroundColor = backgroundColor
        )
    }
}

@Composable
private fun HorizontalDynamicBatteryIcon(
    modifier: Modifier = Modifier,
    batteryPercentage: Int,
    showPercentage: Boolean = false,
    indicationColor: Color = if (batteryPercentage > 20) Color.Green else Color.Red,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier.size(
                WIDTH, HEIGHT
            )
        ) {
            // Draw battery outline
            drawRoundRect(
                color = backgroundColor,
                size = Size(size.width - 4.dp.toPx(), size.height),
                cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
            )
            val batteryLevelWidth = (size.width - 8.dp.toPx()) * (batteryPercentage / 100f)
            // Draw battery level
            drawRoundRect(
                color = indicationColor,
                topLeft = Offset(2.dp.toPx(), 2.dp.toPx()),
                size = Size(
                    batteryLevelWidth, size.height - 4.dp.toPx()
                ),
                cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
            )
            // Draw battery cap
            val path = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(
                            offset = Offset(size.width - 4.dp.toPx(), size.height / 4),
                            size = Size(4.dp.toPx(), size.height / 2),
                        ),
                        topRight = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                        bottomRight = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                    )
                )
            }
            drawPath(path, color = backgroundColor)
        }
        // Display battery percentage
        if (showPercentage) Text(
            text = "$batteryPercentage%",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun VerticalDynamicBatteryIcon(
    modifier: Modifier = Modifier,
    batteryPercentage: Int,
    showPercentage: Boolean = false,
    indicationColor: Color = if (batteryPercentage > 20) Color.Green else Color.Red,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier.size(HEIGHT, WIDTH)
        ) {
            // Draw battery outline
            drawRoundRect(
                color = backgroundColor,
                size = Size(
                    size.width, size.height - 4.dp.toPx()
                ),
                topLeft = Offset(0f, 4.dp.toPx()),
                cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
            )
            val batteryLevelHeight = (size.height - 8.dp.toPx()) * (batteryPercentage / 100f)
            // Draw battery level
            drawRoundRect(
                color = indicationColor,
                topLeft = Offset(2.dp.toPx(), size.height - batteryLevelHeight - 2.dp.toPx()),
                size = Size(
                    size.width - 4.dp.toPx(), batteryLevelHeight
                ),
                cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx())
            )
            // Draw battery cap
            val path = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(
                            offset = Offset(size.width / 4, 0f),
                            size = Size(size.width / 2, 4.dp.toPx() + 1),
                        ),
                        topRight = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                        topLeft = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                    )
                )
            }
            drawPath(path, color = backgroundColor)
        }
        // Display battery percentage
        if (showPercentage) Text(
            text = "$batteryPercentage%",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        BatteryDynamicIcon(batteryPercentage = 75)
        BatteryDynamicIcon(verticalShape = true, batteryPercentage = 20)
    }
}