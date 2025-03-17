package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@Composable
fun FloatingWindowRoute(
    bubbleMessengerViewModel: FloatingWindowViewModel = hiltViewModel(),
    settingsUiStyle: StSettingsUiStyle,
    onBack: () -> Unit,
) {
    val isFloatingViewEnabled = bubbleMessengerViewModel.isFloatingViewEnabled.collectAsState()
    val localContext = LocalContext.current.applicationContext
    var hasOverlayPermission by remember { mutableStateOf(Settings.canDrawOverlays(localContext)) }
    val overlayPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        hasOverlayPermission = Settings.canDrawOverlays(localContext)
        if (Settings.canDrawOverlays(localContext)) {
            Toast.makeText(localContext, "Overlay permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(localContext, "Overlay permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (!Settings.canDrawOverlays(localContext)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:${localContext.packageName}")
            )
            overlayPermissionLauncher.launch(intent)
        }
    }

    FloatingWindowScreen(
        onBack = onBack,
        settingsUiStyle = settingsUiStyle,
        hasOverlayPermission = hasOverlayPermission,
        isShowBubble = isFloatingViewEnabled.value,
        showBubbleEnableChange = {
            bubbleMessengerViewModel.floatingViewEnabledStateChanged(it)
        },
    )
}