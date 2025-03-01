package com.jddev.simpletouch.ui.settingsui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.settingsui.internal.StSettingsItem
import com.jddev.simpletouch.ui.settingsui.internal.headlineTextStyle
import com.jddev.simpletouch.ui.settingsui.internal.supportingTextStyle

@Composable
fun StSettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsCheckBoxItem(
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
fun StSettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsCheckBoxItem(
    modifier = modifier,
    leadingIconPainter = leadingIcon,
    title = title,
    subTitle = subTitle,
    checked = checked,
    enabled = enabled,
    onCheckedChange = onCheckedChange,
)

@Composable
fun StSettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsCheckBoxItem(
    modifier = modifier,
    leadingIconImageVector = leadingIcon,
    title = title,
    subTitle = subTitle,
    checked = checked,
    enabled = enabled,
    onCheckedChange = onCheckedChange,
)

@Composable
private fun StSettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    leadingIconImageVector: ImageVector? = null,
    leadingIconPainter: Painter? = null,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    val disableAlpha = SETTINGS_UI_DISABLE_ALPHA
    StSettingsItem(
        modifier = modifier,
        headlineContent = {
            Text(
                title,
                style = headlineTextStyle,
                modifier = Modifier.alpha(if (enabled) 1f else disableAlpha),
            )
        },
        supportingContent = {
            subTitle?.let {
                Text(
                    it,
                    style = supportingTextStyle,
                    modifier = Modifier.alpha(if (enabled) 1f else disableAlpha),
                )
            }
        },
        trailingContent = {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked -> onCheckedChange?.invoke(checked) },
                enabled = enabled,
            )
        },
        leadingContent = leadingIconImageVector?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier
                        .padding(start = ADDITIONAL_LEADING_ICON_PADDING)
                        .alpha(if (enabled) 1f else disableAlpha),
                )
            }
        } ?: leadingIconPainter?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier
                        .padding(start = ADDITIONAL_LEADING_ICON_PADDING)
                        .alpha(if (enabled) 1f else disableAlpha),
                )
            }
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
private fun Preview() {
    var isChecked by remember { mutableStateOf(false) }
    StUiPreviewWrapper {
        StSettingsCheckBoxItem(
            leadingIcon = Icons.Default.DarkMode,
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )
        StSettingsCheckBoxItem(
            leadingIcon = Icons.Default.DarkMode,
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            enabled = false,
            onCheckedChange = { isChecked = !isChecked },
        )
        StSettingsCheckBoxItem(
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )
    }
}