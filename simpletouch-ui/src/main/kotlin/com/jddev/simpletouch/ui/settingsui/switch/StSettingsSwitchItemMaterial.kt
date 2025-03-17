package com.jddev.simpletouch.ui.settingsui.switch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiSwitch
import com.jddev.simpletouch.ui.foundation.StUiSwitchDefaults
import com.jddev.simpletouch.ui.settingsui.ADDITIONAL_LEADING_ICON_PADDING
import com.jddev.simpletouch.ui.settingsui.StSettingsBaseItem

@Composable
internal fun StSettingsSwitchItemMaterial(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIconPainter: Painter? = null,
    leadingIconImageVector: ImageVector? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    StSettingsBaseItem(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconImageVector = leadingIconImageVector,
        leadingIconPainter = leadingIconPainter,
        trailingContent = {
            StUiSwitch(
                checked = checked,
                onCheckedChange = { switchChecked -> onCheckedChange?.invoke(switchChecked) },
                enabled = enabled,
                colors = StUiSwitchDefaults.colors(
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        onClick = if (enabled && onCheckedChange != null) {
            { onCheckedChange(!checked) }
        } else {
            null
        },
        additionalLeadingIconPadding = ADDITIONAL_LEADING_ICON_PADDING
    )
}


@Composable
@StUiPreview
private fun PreviewEnable() {
    var isChecked by remember { mutableStateOf(false) }
    StUiPreviewWrapper {
        StSettingsSwitchItemMaterial(
            leadingIconImageVector = Icons.Default.DarkMode,
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )

        StSettingsSwitchItemMaterial(
            leadingIconImageVector = Icons.Default.DarkMode,
            title = "Title with long text and overflow of view",
            subTitle = "Enable dark mode and long text with overflow of view",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )

        StSettingsSwitchItemMaterial(
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            enabled = false,
            onCheckedChange = { isChecked = !isChecked },
        )
    }
}