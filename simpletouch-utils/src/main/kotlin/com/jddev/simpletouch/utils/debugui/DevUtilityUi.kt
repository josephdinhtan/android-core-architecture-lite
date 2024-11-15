package com.jddev.simpletouch.utils.debugui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.TextSnippet
import androidx.compose.material.icons.outlined.LogoDev
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiDragBox
import com.jddev.simpletouch.utils.debugui.logging.LogcatBottomSheet
import com.jddev.simpletouch.utils.logging.LogManager

@Composable
fun DevUtilityUi(
    modifier: Modifier = Modifier,
    isEnable: Boolean,
    logManager: LogManager,
    clearLog: (() -> Unit)? = null,
    devControlPanelContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content.invoke()
        if(isEnable) {
            StUiDragBox(modifier = modifier) {
                DevUtilsButton(
                    modifier = Modifier.padding(16.dp),
                    logManager = logManager,
                    clearLog = clearLog,
                    devControlPanelContent = devControlPanelContent
                )
            }
        }
    }
}

@Composable
private fun DevUtilsButton(
    modifier: Modifier = Modifier,
    logManager: LogManager,
    clearLog: (() -> Unit)? = null,
    devControlPanelContent: (@Composable () -> Unit)? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    var showLogcatBottomSheet by remember { mutableStateOf(false) }
    var showDevControlPanelBottomSheet by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        ExtendedFloatingActionButton(modifier = Modifier.defaultMinSize(
            minWidth = 1.dp, minHeight = 1.dp
        ), onClick = { expanded = true }) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Outlined.LogoDev, null)
                Text("Mode")
            }
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("logcat") }, onClick = {
                showLogcatBottomSheet = true
                expanded = false
            }, leadingIcon = {
                Icon(
                    Icons.AutoMirrored.Outlined.TextSnippet, contentDescription = null
                )
            })
            devControlPanelContent?.let {
                HorizontalDivider()
                DropdownMenuItem(text = { Text("Dev panel") }, onClick = {
                    expanded = false
                    showDevControlPanelBottomSheet = true
                }, leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) })
            }
        }
    }

    if (showLogcatBottomSheet) {
        LogcatBottomSheet(logManager,
            clearLog = clearLog,
            onDismissRequest = { showLogcatBottomSheet = false })
    }
    if (showDevControlPanelBottomSheet) {
        DevControlPanelBottomSheet(
            onDismissRequest = { showDevControlPanelBottomSheet = false },
            content = devControlPanelContent
        )
    }
}