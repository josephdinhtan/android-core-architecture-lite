package com.jddev.simpletouch.ui.foundation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper

@Composable
fun StUiSwitch() {
    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        )
    )
}

@StUiPreview
@Composable
fun DefaultPreview() {
    StUiPreviewWrapper {
        StUiSwitch()
    }
}
