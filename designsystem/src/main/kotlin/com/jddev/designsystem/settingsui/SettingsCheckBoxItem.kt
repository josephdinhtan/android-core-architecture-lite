package com.jddev.designsystem.settingsui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsCheckBoxItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    title: String,
    subTitle: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    onClick: (() -> Unit)?,
) {
    val disableAlpha = SETTINGS_UI_DISABLE_ALPHA
    SettingsItem(
        modifier = modifier,
        headlineContent = {
            Text(
                title,
                modifier = Modifier.alpha(if (enabled) 1f else disableAlpha),
            )
        },
        supportingContent = {
            subTitle?.let {
                Text(
                    it,
                    modifier = Modifier.alpha(if (enabled) 1f else disableAlpha),
                )
            }
        },
        trailingContent = {
            Checkbox(
                checked = checked,
                onCheckedChange = null,
                enabled = enabled,
            )
        },
        leadingContent = leadingIcon?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier.alpha(if (enabled) 1f else disableAlpha),
                )
            }
        },
        onClick = if (enabled) onClick else null,
    )
}

@Composable
@Preview
private fun PreviewEnable() {
    var isChecked by remember { mutableStateOf(false) }
    MaterialTheme {
        SettingsCheckBoxItem(
            leadingIcon = Icons.Default.DarkMode,
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            onClick = { isChecked = !isChecked },
        )
    }
}

@Composable
@Preview
private fun PreviewDisable() {
    var isChecked by remember { mutableStateOf(false) }
    MaterialTheme {
        SettingsCheckBoxItem(
            leadingIcon = Icons.Default.DarkMode,
            title = "Dark mode",
            subTitle = "Enable dark mode",
            checked = isChecked,
            enabled = false,
            onClick = { isChecked = !isChecked },
        )
    }
}
