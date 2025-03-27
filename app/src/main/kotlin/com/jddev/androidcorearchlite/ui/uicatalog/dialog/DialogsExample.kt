package com.jddev.androidcorearchlite.ui.uicatalog.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.foundation.dialog.StUiAlertDialog

private val dialogTitle = "Alert dialog example"
private val dialogText = "This is an example of an alert dialog with buttons."
private val icon = Icons.Default.Info

@Composable
fun MaterialAlertDialogExample(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(icon = {
            Icon(icon, contentDescription = "Example Icon")
        }, title = {
            Text(text = dialogTitle)
        }, text = {
            Text(text = dialogText)
        }, onDismissRequest = {
            onDismissRequest()
        }, confirmButton = {
            TextButton(onClick = {
                onConfirmation()
            }) {
                Text("Confirm")
            }
        }, dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                Text("Dismiss")
            }
        })
    }
}

@Composable
fun StUiAlertDialogExample(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    StUiAlertDialog(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        icon = null,
        confirmButtonText = "Follow",
        dismissButtonText = "Cancel",
        title = "Follow me on Instagram",
        text = "@jddev_official",
        onConfirm = onConfirmation,
        onDismiss = onDismissRequest
    )
}