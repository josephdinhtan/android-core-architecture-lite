package com.jddev.simpletouch.utils.debugui.logging

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun LogIndicator(logPriority: Int) {
    val text = when (logPriority) {
        Log.VERBOSE -> "V"
        Log.DEBUG -> "D"
        Log.INFO -> "I"
        Log.WARN -> "W"
        Log.ERROR -> "E"
        Log.ASSERT -> "A"
        else -> "?"
    }
    Box(modifier = Modifier.size(24.dp).background(getBackgroundColor(logPriority))) {
        Text(
            text = text,
            maxLines = 1,
            color = getForegroundColor(logPriority),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

private fun getForegroundColor(logPriority: Int, isLightMode: Boolean = true): Color {
    return if (isLightMode) {
        when (logPriority) {
            Log.VERBOSE -> Color(0xFF000000)
            Log.DEBUG -> Color(0xFF000000)
            Log.INFO -> Color(0xFF414D41)
            Log.WARN -> Color(0xFF000000)
            Log.ERROR -> Color(0xFFFFFFFF)
            Log.ASSERT -> Color(0xFFFFFFFF)
            else -> Color.Unspecified
        }
    } else {
        when (logPriority) {
            Log.VERBOSE -> Color(0xFF000000)
            Log.DEBUG -> Color(0xFFBBBBBB)
            Log.INFO -> Color(0xFFE9F5E6)
            Log.WARN -> Color(0xFF000000)
            Log.ERROR -> Color(0xFF000000)
            Log.ASSERT -> Color(0xFFFFFFFF)
            else -> Color.Unspecified
        }
    }
}

private fun getBackgroundColor(logPriority: Int, isLightMode: Boolean = true): Color {
    return if (isLightMode) {
        when (logPriority) {
            Log.VERBOSE -> Color(0xFFD6D6D6)
            Log.DEBUG -> Color(0xFFCFE7FF)
            Log.INFO -> Color(0xFFE9F5E6)
            Log.WARN -> Color(0xFFF5EAC1)
            Log.ERROR -> Color(0xFFCF5B56)
            Log.ASSERT -> Color(0xFF7F0000)
            else -> Color.Unspecified
        }
    } else {
        when (logPriority) {
            Log.VERBOSE -> Color(0xFFD6D6D6)
            Log.DEBUG -> Color(0xFF305D78)
            Log.INFO -> Color(0xFF6A8759)
            Log.WARN -> Color(0xFFBBB529)
            Log.ERROR -> Color(0xFFCF5B56)
            Log.ASSERT -> Color(0xFF8B3C3C)
            else -> Color.Unspecified
        }
    }
}