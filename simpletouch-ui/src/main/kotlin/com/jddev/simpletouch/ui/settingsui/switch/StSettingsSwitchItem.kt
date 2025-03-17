package com.jddev.simpletouch.ui.settingsui.switch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.jddev.simpletouch.ui.StUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

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
    when (StUi.settingsUiStyle) {
        StSettingsUiStyle.Cupertino -> StSettingsSwitchItemCupertino(
            modifier = modifier,
            title = title,
            subTitle = subTitle,
            leadingIconImageVector = leadingIconImageVector,
            leadingIconPainter = leadingIconPainter,
            checked = checked,
            enabled = enabled,
            onCheckedChange = onCheckedChange,
        )

        else -> StSettingsSwitchItemMaterial(
            modifier = modifier,
            title = title,
            subTitle = subTitle,
            leadingIconImageVector = leadingIconImageVector,
            leadingIconPainter = leadingIconPainter,
            checked = checked,
            enabled = enabled,
            onCheckedChange = onCheckedChange,
        )
    }
}
