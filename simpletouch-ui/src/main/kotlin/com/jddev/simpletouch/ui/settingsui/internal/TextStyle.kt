package com.jddev.simpletouch.ui.settingsui.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val groupTableTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        lineHeight = 16.sp,
    )

val headlineTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = MaterialTheme.colorScheme.onSurface,
        lineHeight = 22.sp,
    )
    //@Composable get() = MaterialTheme.typography.titleLarge

val supportingTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        lineHeight = 20.sp,
    )

val cupertinoValueTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        lineHeight = 22.sp,
        letterSpacing = (-0.43).sp
    )