package com.jddev.androidcorearchlite.ui.basic.notification

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NotificationUiRoute(
    notificationUiViewModel: NotificationUiViewModel = hiltViewModel(),
    navigateToDetailNotification: (String) -> Unit,
    onBack: () -> Unit,
) {
    NotificationUiScreen(
        onBack = onBack,
        showSimpleNotification = { notificationUiViewModel.showSimpleNotification() },
        updateNotification = { notificationUiViewModel.updateNotification() },
        cancelNotification = { notificationUiViewModel.cancelNotification() },
        navigateToDetailNotification = navigateToDetailNotification
    )
}