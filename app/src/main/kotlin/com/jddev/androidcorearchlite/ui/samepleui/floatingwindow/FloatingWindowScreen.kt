package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.settingsui.StSettingsSwitchItem
import com.jddev.simpletouch.ui.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingWindowScreen(
    onBack: () -> Unit,
    hasOverlayPermission: Boolean,
    isShowBubble: Boolean,
    showBubbleEnableChange: (Boolean) -> Unit,
) {
    Scaffold(topBar = {
        StUiTopAppBar(
            title = "Messenger Bubble", onBack = onBack
        )
    }) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            if (!hasOverlayPermission) {
                Text("Overlay permission is not granted")
            } else {
                StSettingsUi(
                    uiStyle = StSettingsUiStyle.Material,
                ) {
                    StSettingsGroup {
                        StSettingsSwitchItem(
                            title = "Chat heads",
                            checked = isShowBubble,
                            onCheckedChange = showBubbleEnableChange,
                        )
                        StSettingsSwitchItem(
                            title = "Gaming UI",
                            checked = false,
                            onCheckedChange = { },
                        )
                    }
                }
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    var isChecked by remember { mutableStateOf(false) }
    StUiPreviewWrapper {
        FloatingWindowScreen(
            onBack = {},
            isShowBubble = isChecked,
            hasOverlayPermission = true,
            showBubbleEnableChange = { isChecked = it },
        )
    }
}