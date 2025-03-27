package com.jddev.simpletouch.ui.foundation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun StUiCommonDialog(
    title: String? = null,
    message: String? = null,
    iconData: ImageVector = Icons.Default.Info,
    onConfirmRequest: (() -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
) {
    AlertDialog(
        icon = {
            Icon(iconData, contentDescription = null)
        },
        title = {
            title?.let { Text(text = it) }
        },
        text = {
            message?.let { Text(text = it) }
        },
        onDismissRequest = onDismissRequest ?: {},
        dismissButton = {
            onDismissRequest?.let {
                TextButton(onClick = it) {
                    Text("Cancel")
                }
            }
        },
        confirmButton = {
            onConfirmRequest?.let {
                TextButton(onClick = {
                    it()
                }) {
                    Text("OK")
                }
            }
        },
    )
}