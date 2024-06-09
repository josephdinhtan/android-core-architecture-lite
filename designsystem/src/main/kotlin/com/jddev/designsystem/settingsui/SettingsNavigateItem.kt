package com.jddev.designsystem.settingsui

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsNavigateItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    title: String,
    subTitle: String? = null,
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
                Text(it, modifier = Modifier.alpha(if (enabled) 1f else disableAlpha))
            }
        },
        trailingContent = {
            Icon(
                Icons.Filled.ArrowForwardIos,
                "navigate",
                modifier = Modifier.alpha(if (enabled) 1f else disableAlpha).height(18.dp),
            )
        },
        leadingContent =
            leadingIcon?.let {
                {
                    Icon(
                        it,
                        "leading",
                        modifier = Modifier.alpha(if (enabled) 1f else disableAlpha),
                    )
                }
            },
        onClick = if(enabled) onClick else null,
    )
}

@Composable
@Preview
private fun PreviewEnable() {
    MaterialTheme {
        SettingsNavigateItem(
            leadingIcon = Icons.Default.Language,
            title = "Language",
            subTitle = "English",
            enabled = true,
            onClick = { },
        )
    }
}

@Composable
@Preview
private fun PreviewDisable() {
    SettingsNavigateItem(
        leadingIcon = Icons.Default.Language,
        title = "Language",
        subTitle = "English",
        enabled = false,
        onClick = { },
    )
}
