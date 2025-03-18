package com.jddev.simpletouch.ui.customization.qrcodeview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

/**
 * A composable that renders a QR code from a data string.
 *
 * @param data The data to encode into a QR code.
 * @param modifier The Compose modifier to apply to the QR code.
 * @param colors The color palette to use for the QR code - defaults to black and white.
 * @param dotShape The shape of the individual dots in the QR code - defaults to SoftCurve.
 * @param encoder The encoder to use to encode the data into a QR code. Meant to be able to stub out in tests if needed.
 * @param overlayContent Optional content to overlay on top of the QR code. This overlay is limited to 25% of the size
 *      of the QR code and will be positioned in the center of it.
 */
@Composable
fun QrCodeView(
    data: String,
    modifier: Modifier = Modifier,
    colors: QrCodeColors = QrCodeColors.default(),
    dotShape: QrStyle = QrStyle.SoftCurve,
    encoder: QrEncoder = ZxingQrEncoder(),
    overlayContent: (@Composable () -> Unit)? = null,
) {
    Box(modifier = modifier.aspectRatio(1f), contentAlignment = Alignment.Center) {
        QrCodeView(
            data,
            modifier = Modifier.fillMaxSize(),
            colors = colors,
            dotShape = dotShape,
            encoder = encoder
        )
        if (overlayContent != null) {
            val roundedCornerShape = RoundedCornerShape(
                when (dotShape) {
                    QrStyle.SoftCurve -> 30
                    QrStyle.Square -> 0
                    QrStyle.Circle -> 100
                }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize(fraction = 0.25f)
                    .clip(roundedCornerShape)
                    .background(colors.background)
                    .padding(1.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(roundedCornerShape)
                        .background(colors.foreground)
                        .padding(4.dp)
                ) {
                    overlayContent()
                }
            }
        }
    }
}

@Composable
private fun QrCodeView(
    data: String,
    modifier: Modifier = Modifier,
    colors: QrCodeColors = QrCodeColors.default(),
    dotShape: QrStyle = QrStyle.Square,
    encoder: QrEncoder = ZxingQrEncoder()
) {
    val encodedData = remember(data, encoder) { encoder.encode(data) }
    Canvas(modifier = modifier.background(colors.background)) {
        encodedData?.let { matrix ->
            val cellSize = size.width / matrix.width
            for (x in 0 until matrix.width) {
                for (y in 0 until matrix.height) {
                    if (matrix.get(x, y) != 1.toByte() || isFinderCell(x, y, matrix.width)) continue
                    when (dotShape) {
                        QrStyle.SoftCurve, QrStyle.Square -> drawRect(
                            color = colors.foreground,
                            topLeft = Offset(x * cellSize, y * cellSize),
                            size = Size(cellSize, cellSize)
                        )

                        QrStyle.Circle -> drawCircle(
                            color = colors.foreground, center = Offset(
                                x * cellSize + cellSize / 2, y * cellSize + cellSize / 2
                            ), radius = cellSize / 2
                        )
                    }
                }
            }
            drawFinderSquares(cellSize, colors, dotShape)
        }
    }
}

@Composable
@StUiPreview
private fun QrCodeViewPreview() {
    StUiPreviewWrapper {
        Box(modifier = Modifier.size(300.dp)) {
            QrCodeView("123-456-789",
                modifier = Modifier.fillMaxWidth(),
                dotShape = QrStyle.SoftCurve,
                overlayContent = {
                    Icon(
                        Icons.Default.Call,
                        null,
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                })
        }
    }
}
