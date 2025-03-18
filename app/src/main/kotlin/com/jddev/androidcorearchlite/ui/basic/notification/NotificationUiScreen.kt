package com.jddev.androidcorearchlite.ui.basic.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsBaseItem
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationUiScreen(
    settingsUiStyle: StSettingsUiStyle,
    showSimpleNotification: () -> Unit,
    updateNotification: () -> Unit,
    cancelNotification: () -> Unit,
    navigateToDetailNotification: (String) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            StUiTopAppBar(
                title = "Notification",
                onBack = onBack,
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            StSettingsUi(
                uiStyle = StSettingsUiStyle.Cupertino,
            ) {
                StSettingsGroup {
                    StSettingsBaseItem (
                        title = "Simple notification",
                        subTitle = "Show an notification",
                        onClick = showSimpleNotification
                    )
                }
            }
            StSettingsUi(
                uiStyle = StSettingsUiStyle.Cupertino,
            ) {
                StSettingsGroup {
                    StSettingsBaseItem(
                        title = "Update notification",
                        onClick = updateNotification
                    )
                }
            }
            StSettingsUi(
                uiStyle = StSettingsUiStyle.Cupertino,
            ) {
                StSettingsGroup {
                    StSettingsBaseItem(
                        title = "Cancel notification",
                        onClick = cancelNotification
                    )
                }
            }
            StSettingsUi(
                uiStyle = StSettingsUiStyle.Cupertino,
            ) {
                StSettingsGroup {
                    StSettingsBaseItem(
                        title = "Go to notification detail ",
                        onClick = { navigateToDetailNotification("Form action \"Navigate to notification detail\"") }
                    )
                }
            }
        }

    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        NotificationUiScreen(
            settingsUiStyle = StSettingsUiStyle.Cupertino,
            {}, {}, {}, {}, {}
        )
    }
}
