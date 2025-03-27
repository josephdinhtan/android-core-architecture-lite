package com.jddev.simpletouch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.theme.sh.ShTheme

@Composable
fun StUiTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = ShTheme (
    isDarkTheme = isDarkTheme, content = content
)