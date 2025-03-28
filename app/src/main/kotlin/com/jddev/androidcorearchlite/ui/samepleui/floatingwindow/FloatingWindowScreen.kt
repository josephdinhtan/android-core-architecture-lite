package com.jddev.androidcorearchlite.ui.samepleui.floatingwindow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingWindowScreen(
    hasOverlayPermission: Boolean,
    isServiceRunning: Boolean,
    isShowBubble: Boolean,
    onServiceStateChange: (Boolean) -> Unit,
    showBubbleEnableChange: (Boolean) -> Unit,
    onBack: () -> Unit,
) {
    StUiSimpleScaffold(
        title = "Floating windows", onBack = onBack
    ) {
        if (!hasOverlayPermission) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Overlay permission is not granted")
            }
        } else {
            StSettingsUi {
                StSettingsGroup {
                    StSettingsSwitchItem(
                        title = "Start Service",
                        subTitle = "Start service to show floating window",
                        checked = isServiceRunning,
                        onCheckedChange = onServiceStateChange,
                    )
                    StSettingsSwitchItem(
                        title = "Chat heads",
                        subTitle = "Show chat heads like messenger",
                        enabled = isServiceRunning,
                        checked = isShowBubble,
                        onCheckedChange = showBubbleEnableChange,
                    )
                }
                StSettingsGroup {
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
            onServiceStateChange = {},
            isServiceRunning = false,
        )
    }
}