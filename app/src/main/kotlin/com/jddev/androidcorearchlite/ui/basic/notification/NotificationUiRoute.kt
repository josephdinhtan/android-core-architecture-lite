package com.jddev.androidcorearchlite.ui.basic.notification

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

@Composable
fun NotificationUiRoute(
    notificationUiViewModel: NotificationUiViewModel = hiltViewModel(),
    settingsUiStyle: StSettingsUiStyle,
    navigateToDetailNotification: (String) -> Unit,
    onBack: () -> Unit,
) {
    NotificationUiScreen(
        settingsUiStyle = settingsUiStyle,
        onBack = onBack,
        showSimpleNotification = { notificationUiViewModel.showSimpleNotification() },
        updateNotification = { notificationUiViewModel.updateNotification() },
        cancelNotification = { notificationUiViewModel.cancelNotification() },
        navigateToDetailNotification = navigateToDetailNotification
    )
}