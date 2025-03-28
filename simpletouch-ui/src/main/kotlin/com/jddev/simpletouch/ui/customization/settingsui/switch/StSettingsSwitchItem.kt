package com.jddev.simpletouch.ui.customization.settingsui.switch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsItemBase
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.foundation.StUiSwitch
import com.jddev.simpletouch.ui.foundation.StUiSwitchDefaults
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StSettingsSwitchItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingImageVector: ImageVector? = null,
    leadingIconPainter: Painter? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    StSettingsItemBase(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingContent = leadingImageVector?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier.alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingIconPainter?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier.alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingContent,
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
    )
}


@Composable
@StUiPreview
private fun PreviewEnable() {
    var isChecked by remember { mutableStateOf(false) }
    StUiPreviewWrapper {
        StSettingsSwitchItem(
            leadingImageVector = Icons.Default.DarkMode,
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )

        StSettingsSwitchItem(
            leadingImageVector = Icons.Default.DarkMode,
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