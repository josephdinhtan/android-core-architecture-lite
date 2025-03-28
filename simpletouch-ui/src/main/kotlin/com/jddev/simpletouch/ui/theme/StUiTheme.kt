package com.jddev.simpletouch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.theme.standard.StUiStandardTheme

@Composable
fun StUiTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = StUiStandardTheme (
    isDarkTheme = isDarkTheme, content = content
)