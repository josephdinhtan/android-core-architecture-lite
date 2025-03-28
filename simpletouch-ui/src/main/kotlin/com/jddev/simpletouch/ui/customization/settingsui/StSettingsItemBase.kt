package com.jddev.simpletouch.ui.customization.settingsui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.jddev.simpletouch.ui.foundation.StUiListItemBase
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StSettingsItemBase(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val settingsItemColors = StSettingsUi.colors
    StUiListItemBase(
        modifier = modifier,
        headlineContent = {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge.copy(color = settingsItemColors.titleColor),
                modifier = Modifier.alpha(if (enabled) 1f else settingsItemColors.disableAlpha),
            )
        },
        supportingContent = {
            subTitle?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.titleMedium.copy(color = settingsItemColors.subTitleColor),
                    modifier = Modifier.alpha(if (enabled) 1f else settingsItemColors.disableAlpha),
                )
            }
        },
        trailingContent = trailingContent,
        leadingContent = leadingContent,
        onClick = if (enabled && onClick != null) {
            { onClick() }
        } else {
            null
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@StUiPreview
@Composable
private fun Preview() {
    StUiPreviewWrapper {
        StSettingsUi {
            StSettingsItemBase(
                title = "This is the Title",
                subTitle = "This is the Subtitle",
                trailingContent = { Icon(Icons.AutoMirrored.Filled.NavigateNext, "trailing") },
                onClick = {},
            )
        }
    }
}
