package com.simpletouch.ui.settingsui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsNavigateItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    title: String,
    subTitle: String? = null,
    subTitleColor: Color = LocalTextStyle.current.color,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
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
                    color = subTitleColor
                )
            }
        },
        trailingContent = {
            onClick?.let {
                Icon(
                    Icons.Filled.ArrowForwardIos,
                    "navigate",
                    modifier = Modifier
                        .alpha(if (enabled) 1f else disableAlpha)
                        .height(18.dp),
                )
            }
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
        onClick = if (enabled) onClick else null,
    )
}

@Composable
@Preview
private fun PreviewEnable() {
    MaterialTheme {
        Scaffold {
            Column(modifier = Modifier.padding(it)) {
                SettingsNavigateItem(
                    leadingIcon = Icons.Default.Settings,
                    title = "Enable",
                    subTitle = "Can navigate",
                    enabled = true,
                    onClick = { },
                )

                SettingsNavigateItem(
                    leadingIcon = Icons.Default.Language,
                    title = "Enable",
                    subTitle = "Can not navigate",
                    enabled = true,
                    onClick = null,
                )

                SettingsNavigateItem(
                    leadingIcon = Icons.Default.DateRange,
                    title = "Disable",
                    subTitle = "Can not navigate",
                    enabled = false,
                    onClick = { },
                )
            }
        }
    }
}
