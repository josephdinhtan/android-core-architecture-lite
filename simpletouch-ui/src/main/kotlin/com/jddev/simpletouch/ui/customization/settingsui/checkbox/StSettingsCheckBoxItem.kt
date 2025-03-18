package com.jddev.simpletouch.ui.customization.settingsui.checkbox

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CellTower
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.StUi
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiCircleCheckbox
import com.jddev.simpletouch.ui.customization.settingsui.ADDITIONAL_LEADING_ICON_PADDING
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsBaseItem
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

@Composable
fun StSettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    circleShape: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsCheckBoxItem(
    modifier = modifier,
    leadingIconImageVector = null,
    leadingIconPainter = null,
    title = title,
    subTitle = subTitle,
    checked = checked,
    enabled = enabled,
    circleShape = circleShape,
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
    circleShape: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsCheckBoxItem(
    modifier = modifier,
    leadingIconPainter = leadingIcon,
    title = title,
    subTitle = subTitle,
    checked = checked,
    enabled = enabled,
    circleShape = circleShape,
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
    circleShape: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) = StSettingsCheckBoxItem(
    modifier = modifier,
    leadingIconImageVector = leadingIcon,
    title = title,
    subTitle = subTitle,
    checked = checked,
    enabled = enabled,
    circleShape = circleShape,
    onCheckedChange = onCheckedChange,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StSettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    leadingIconImageVector: ImageVector? = null,
    leadingIconPainter: Painter? = null,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    circleShape: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    StSettingsBaseItem(modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconImageVector = leadingIconImageVector,
        leadingIconPainter = leadingIconPainter,
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
        additionalLeadingIconPadding = if (StUi.settingsUiStyle == StSettingsUiStyle.Cupertino) 0.dp
        else ADDITIONAL_LEADING_ICON_PADDING)
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
            checked = !isChecked,
            onCheckedChange = { isChecked = !it },
        )
        StSettingsCheckBoxItem(
            leadingIcon = Icons.Default.CellTower,
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
            leadingIcon = Icons.Default.LooksOne,
            title = "Enable One",
            subTitle = "Enable One will help something",
            checked = !isChecked,
            circleShape = true,
            onCheckedChange = { isChecked = !it },
        )
        StSettingsCheckBoxItem(
            leadingIcon = Icons.Default.CellTower,
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