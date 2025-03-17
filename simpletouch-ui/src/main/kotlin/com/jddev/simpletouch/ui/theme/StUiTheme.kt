package com.jddev.simpletouch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.theme.ios.StUiCupertinoTheme

@Composable
fun StUiTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = StUiCupertinoTheme (
    isDarkTheme = isDarkTheme, content = content
)