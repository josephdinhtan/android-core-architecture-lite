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
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingWindowScreen(
    settingsUiStyle: StSettingsUiStyle,
    hasOverlayPermission: Boolean,
    isShowBubble: Boolean,
    showBubbleEnableChange: (Boolean) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(topBar = {
        StUiTopAppBar(
            title = "Floating windows", onBack = onBack
        )
    }) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            if (!hasOverlayPermission) {
                Text("Overlay permission is not granted")
            } else {
                StSettingsUi(
                    uiStyle = settingsUiStyle,
                ) {
                    StSettingsGroup {
                        StSettingsSwitchItem(
                            title = "Chat heads",
                            subTitle = "Show chat heads like messenger",
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
            settingsUiStyle = StSettingsUiStyle.Cupertino,
            onBack = {},
            isShowBubble = isChecked,
            hasOverlayPermission = true,
            showBubbleEnableChange = { isChecked = it },
        )
    }
}