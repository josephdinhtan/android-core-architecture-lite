package com.jddev.simpletouch.ui.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper

@Composable
fun StUiSwitch(
    checked: Boolean,
    enabled: Boolean = true,
    colors: SwitchColors = StUiSwitchDefaults.colors(),
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Switch(
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        thumbContent = { Box(Modifier.size(SwitchDefaults.IconSize)) },
        colors = colors
    )
}

internal object StUiSwitchDefaults {
    @Composable
    fun colors(
        checkedThumbColor: Color = Color.White,
        checkedTrackColor: Color = MaterialTheme.colorScheme.primary,
        uncheckedThumbColor: Color = Color.White,
        uncheckedTrackColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
        uncheckedBorderColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
    ): SwitchColors = SwitchDefaults.colors(
        checkedTrackColor = checkedTrackColor,
        checkedThumbColor = checkedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedBorderColor = uncheckedBorderColor,
    )
}

@StUiPreview
@Composable
fun DefaultPreview() {
    StUiPreviewWrapper {
        var checked by remember { mutableStateOf(false) }
        StUiSwitch(checked, onCheckedChange = { checked = it })
        StUiSwitch(!checked, onCheckedChange = { checked = !it })
    }
}
