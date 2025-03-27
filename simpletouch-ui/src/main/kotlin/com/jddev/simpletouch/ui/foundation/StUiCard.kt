package com.jddev.simpletouch.ui.foundation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StUiCard(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.shape,
    colors: Color = MaterialTheme.colorScheme.surface,
    border: BorderStroke? = null,
    elevation: Dp = 10.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = colors,
        tonalElevation = 0.dp,
        shadowElevation = elevation,
        border = border,
    ) {
        Column(
            modifier = onClick?.let {
                Modifier.clickable { it() }
            } ?: Modifier,
            content = content
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper(fullScreen = true) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                8.dp, Alignment.CenterVertically
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StUiCard(
                modifier = Modifier.padding(16.dp),
                onClick = {},
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text("This is Demo card")
                }
            }
            StUiCard(
                modifier = Modifier.padding(16.dp),
                onClick = {},
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text("Standard card")
                }
            }
            StUiCard(
                modifier = Modifier.padding(16.dp),
                onClick = {},
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text("A Card")
                }
            }
        }
    }
}