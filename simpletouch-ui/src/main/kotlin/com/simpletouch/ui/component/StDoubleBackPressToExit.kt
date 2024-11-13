package com.simpletouch.ui.component

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

sealed interface BackPress {
    data object Idle : BackPress
    data object InitialTouch : BackPress
}

@Composable
fun StDoubleBackPressToExit(
    toastMessage: String? = "Press again to exit"
) {
    var showToast by remember { mutableStateOf(false) }
    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if (showToast) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        showToast = false
    }

    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(1500)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }
}