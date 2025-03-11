package com.jddev.simpletouch.ui.foundation

import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun StUiHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = MaterialTheme.colorScheme.surfaceContainerHighest
) {
    HorizontalDivider(modifier, thickness, color)
//    Box(
//        modifier.height(thickness).fillMaxWidth().padding(horizontal = 2.dp).background(
//            MaterialTheme.colorScheme.surfaceContainerHighest,
//            shape = RoundedCornerShape(4.dp)
//        )
//    )
}

@Composable
fun StUiVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = MaterialTheme.colorScheme.surfaceContainerHighest
) {
    VerticalDivider(modifier, thickness, color)
//    Box(
//        modifier
//            .width(thickness)
//            .fillMaxHeight()
//            .padding(horizontal = 2.dp)
//            .background(
//                MaterialTheme.colorScheme.surfaceContainerHighest,
//                shape = RoundedCornerShape(4.dp)
//            )
//    )
}