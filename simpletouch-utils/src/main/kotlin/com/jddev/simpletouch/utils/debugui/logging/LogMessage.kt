package com.jddev.simpletouch.utils.debugui.logging

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun LogMessage(logPriority: Int, message: String) {
    val foregroundColor = when (logPriority) {
        Log.VERBOSE -> Color(0xFFBBBBBB)
        Log.DEBUG -> Color(0xFF299999)
        Log.INFO -> Color(0xFFABC023)
        Log.WARN -> Color(0xFFBBB529)
        Log.ERROR -> Color(0xFFFF6B68)
        Log.ASSERT -> Color(0xFFFF6B68)
        else -> Color.Unspecified
    }
    Text(
        text = message,
        maxLines = 1,
        color = foregroundColor,
        textAlign = TextAlign.Center,
    )
}