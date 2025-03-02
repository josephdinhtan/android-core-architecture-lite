package com.jddev.simpletouch.ui.settingsui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CellTower
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material3.Checkbox
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
    StSettingsItem(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconImageVector = leadingIconImageVector,
        leadingIconPainter = leadingIconPainter,
        trailingContent = {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked -> onCheckedChange?.invoke(checked) },
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
private fun Preview() {
    var isChecked by remember { mutableStateOf(false) }
    StUiPreviewWrapper {
        StSettingsCheckBoxItem(
            leadingIcon = Icons.Default.LooksOne,
            title = "Enable One",
            subTitle = "Enable One will help something",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )
        StSettingsCheckBoxItem(
            leadingIcon = Icons.Default.CellTower,
            title = "Enable mode two",
            subTitle = "Enable two will help something",
            checked = isChecked,
            enabled = false,
            onCheckedChange = { isChecked = !isChecked },
        )
        StSettingsCheckBoxItem(
            title = "Enable mode three",
            subTitle = "Enable three will help something",
            checked = isChecked,
            onCheckedChange = { isChecked = !isChecked },
        )
    }
}