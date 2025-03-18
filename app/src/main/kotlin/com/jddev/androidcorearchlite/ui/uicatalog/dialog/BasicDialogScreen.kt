package com.jddev.androidcorearchlite.ui.uicatalog.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.dialog.StUiBaseDialog
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsBaseItem
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDialogScreen(
    onBack: () -> Unit,
) {
    var showMaterialAlertDialog by remember { mutableStateOf(false) }
    var showStUiBaseDialog by remember { mutableStateOf(false) }
    var showStUiAlertDialog by remember { mutableStateOf(false) }
    StUiSimpleScaffold(
        title = "Dialog",
        onBack = onBack
    ) {
        StSettingsUi(uiStyle = StSettingsUiStyle.Cupertino) {
            StSettingsGroup {
                StSettingsBaseItem(
                    title = "Material Alert Dialog",
                    onClick = {
                        showMaterialAlertDialog = !showMaterialAlertDialog
                    }
                )
            }
            StSettingsGroup {
                StSettingsBaseItem(
                    title = "Show StUi Basic Dialog",
                    onClick = {
                        showStUiBaseDialog = !showStUiBaseDialog
                    }
                )
            }
            StSettingsGroup {
                StSettingsBaseItem(
                    title = "Show StUi Alert Dialog",
                    onClick = {
                        showStUiAlertDialog = !showStUiAlertDialog
                    }
                )
            }
        }
    }

    MaterialAlertDialogExample(
        showDialog = showMaterialAlertDialog,
        onDismissRequest = { showMaterialAlertDialog = false },
        onConfirmation = {
            showMaterialAlertDialog = false
        },
    )

    StUiBaseDialog(
        showDialog = showStUiBaseDialog,
        onDismissRequest = { showStUiBaseDialog = false },
        content = {
            Column (
                modifier = Modifier.width(300.dp)
            ) {
                Text("This is a test alert dialog", style = TextStyle(color = MaterialTheme.colorScheme.onSurface))
                Box(Modifier.fillMaxWidth().clickable { showStUiBaseDialog = false }) {
                    Text("Close", style = TextStyle(color = MaterialTheme.colorScheme.onSurface))
                }
            }
        }
    )

    StUiAlertDialogExample(
        showDialog = showStUiAlertDialog,
        onDismissRequest = { showStUiAlertDialog = false },
        onConfirmation = {
            showStUiAlertDialog = false
        },
    )
}