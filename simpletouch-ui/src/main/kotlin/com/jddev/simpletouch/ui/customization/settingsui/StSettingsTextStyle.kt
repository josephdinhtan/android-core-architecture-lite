package com.jddev.simpletouch.ui.customization.settingsui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jddev.simpletouch.ui.theme.ios.CupertinoTheme

val cupertinoGroupTableTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = CupertinoTheme.colorScheme.secondaryLabel.copy(alpha = 0.6f),
        lineHeight = 16.sp,
    )

val headlineTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = CupertinoTheme.colorScheme.primaryLabel,
        lineHeight = 23.sp,
    )
    //@Composable get() = MaterialTheme.typography.titleLarge

val supportingTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = CupertinoTheme.colorScheme.secondaryLabel.copy(alpha = 0.6f),
        lineHeight = 20.sp,
    )

val cupertinoValueTextStyle: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = CupertinoTheme.colorScheme.secondaryLabel.copy(alpha = 0.6f),
        lineHeight = 22.sp,
        letterSpacing = (-0.43).sp
    )