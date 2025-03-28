package com.jddev.simpletouch.ui.customization.settingsui.checkbox

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CellTower
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsItemBase
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.foundation.StUiCircleCheckbox
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StSettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingImageVector: ImageVector? = null,
    leadingIconPainter: Painter? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    circleShape: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    StSettingsItemBase(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingContent =
        leadingImageVector?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier
                        .alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingIconPainter?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier
                        .alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingContent,
        trailingContent = {
            CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                if (circleShape) {
                    StUiCircleCheckbox(
                        checked = checked,
                        onCheckedChange = { checked -> onCheckedChange?.invoke(checked) },
                        enabled = enabled,
                    )
                } else {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked -> onCheckedChange?.invoke(checked) },
                        enabled = enabled,
                    )
                }
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
            leadingImageVector = Icons.Default.LooksOne,
            title = "Enable One",
            subTitle = "Enable One will help something",
            checked = !isChecked,
            onCheckedChange = { isChecked = !it },
        )
        StSettingsCheckBoxItem(
            leadingImageVector = Icons.Default.CellTower,
            title = "Enable mode two",
            subTitle = "Enable two will help something",
            checked = isChecked,
            enabled = false,
            onCheckedChange = { isChecked = it },
        )
        StSettingsCheckBoxItem(
            title = "Enable mode three",
            subTitle = "Enable three will help something",
            checked = isChecked,
            onCheckedChange = { isChecked = it },
        )
        StSettingsCheckBoxItem(
            leadingImageVector = Icons.Default.LooksOne,
            title = "Enable One",
            subTitle = "Enable One will help something",
            checked = !isChecked,
            circleShape = true,
            onCheckedChange = { isChecked = !it },
        )
        StSettingsCheckBoxItem(
            leadingImageVector = Icons.Default.CellTower,
            title = "Enable mode two",
            subTitle = "Enable two will help something",
            checked = isChecked,
            enabled = false,
            circleShape = true,
            onCheckedChange = { isChecked = it },
        )
        StSettingsCheckBoxItem(
            title = "Enable mode three",
            subTitle = "Enable three will help something",
            checked = isChecked,
            circleShape = true,
            onCheckedChange = { isChecked = it },
        )
    }
}