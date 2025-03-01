package com.jddev.simpletouch.ui.settingsui.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val headlineTextStyle: TextStyle
    @Composable get() = MaterialTheme.typography.titleLarge

val supportingTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSurface,
        lineHeight = 16.sp,
    )