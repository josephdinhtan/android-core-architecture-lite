package com.jddev.androidcorearchlite.ui.basic.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationUiScreen(
    showSimpleNotification: () -> Unit,
    updateNotification: () -> Unit,
    cancelNotification: () -> Unit,
    navigateToDetailNotification: (String) -> Unit,
    onBack: () -> Unit,
) {
    StUiSimpleScaffold(
        title = "Notification",
        onBack = onBack,
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Spacer(Modifier.height(32.dp))
                ActionButton(
                    title = "Show a simple notification",
                    onClick = showSimpleNotification
                )
                ActionButton(
                    title = "Update notification",
                    color = MaterialTheme.colorScheme.secondary,
                    onClick = updateNotification
                )
                ActionButton(
                    title = "Cancel notification",
                    color = MaterialTheme.colorScheme.secondary,
                    onClick = cancelNotification
                )
                Spacer(Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(Modifier.height(8.dp))
                ActionButton(
                    title = "Go to notification detail ",
                    color = MaterialTheme.colorScheme.tertiary,
                    onClick = { navigateToDetailNotification("Form action \"Navigate to notification detail\"") }
                )
            }
            Column {
                Icon(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Infomation"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.labelLarge,
                    text = "Click on notification to be redirected to Notification detail screen. That's how we can open the app from a notification"
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun ActionButton(
    title: String,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = color)
    ) {
        Text(title)
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        NotificationUiScreen(
            {}, {}, {}, {}, {}
        )
    }
}
