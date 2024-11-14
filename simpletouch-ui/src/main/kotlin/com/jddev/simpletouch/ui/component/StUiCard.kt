package com.jddev.simpletouch.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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