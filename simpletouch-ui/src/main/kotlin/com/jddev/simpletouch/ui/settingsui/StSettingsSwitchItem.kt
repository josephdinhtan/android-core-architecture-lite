package com.jddev.simpletouch.ui.settingsui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Switch
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
import com.jddev.simpletouch.ui.settingsui.internal.StSettingsItem

@Composable
fun StSettingsSwitchItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsSwitchItem(
    modifier = modifier,
    leadingIconImageVector = null,
    leadingIconPainter = null,
    title = title,
    subTitle = subTitle,
    checked = checked,
    enabled = enabled,
    onCheckedChange = onCheckedChange,
)

@Composable
fun StSettingsSwitchItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIcon: ImageVector? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsSwitchItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconImageVector = leadingIcon,
    checked = checked,
    enabled = enabled,
    onCheckedChange = onCheckedChange,
)

@Composable
fun StSettingsSwitchItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIcon: Painter? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsSwitchItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconPainter = leadingIcon,
    checked = checked,
    enabled = enabled,
    onCheckedChange = onCheckedChange,
)

@Composable
private fun StSettingsSwitchItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIconPainter: Painter? = null,
    leadingIconImageVector: ImageVector? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    StSettingsItem(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconImageVector = leadingIconImageVector,
        leadingIconPainter = leadingIconPainter,
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = { switchChecked -> onCheckedChange?.invoke(switchChecked) },
                enabled = enabled,
            )
        },
        onClick = if (enabled && onCheckedChange != null) {
            { onCheckedChange(!checked) }
        } else {
            null
        },
    )
}

@Composable
@StUiPreview
private fun PreviewEnable() {
    var isChecked by remember { mutableStateOf(false) }
    StUiPreviewWrapper {
        StSettingsSwitchItem(
            leadingIcon = Icons.Default.DarkMode,
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )

        StSettingsSwitchItem(
            leadingIcon = Icons.Default.DarkMode,
            title = "Title with long text and overflow of view",
            subTitle = "Enable dark mode and long text with overflow of view",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )

        StSettingsSwitchItem(
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            enabled = false,
            onCheckedChange = { isChecked = !isChecked },
        )
    }
}
