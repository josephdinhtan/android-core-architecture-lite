package com.jddev.simpletouch.ui.settingsui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiBaseListItem
import com.jddev.simpletouch.ui.settingsui.internal.headlineTextStyle
import com.jddev.simpletouch.ui.settingsui.internal.supportingTextStyle

@Composable
fun StSettingsBaseItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) = StSettingsBaseItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconPainter = null,
    leadingIconImageVector = null,
    leadingContent = null,
    trailingContent = trailingContent,
    enabled = enabled,
    onClick = onClick,
)

@Composable
fun StSettingsBaseItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leading: Painter? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) = StSettingsBaseItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconPainter = leading,
    trailingContent = trailingContent,
    enabled = enabled,
    onClick = onClick,
)

@Composable
fun StSettingsBaseItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leading: ImageVector? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) = StSettingsBaseItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconImageVector = leading,
    trailingContent = trailingContent,
    enabled = enabled,
    onClick = onClick,
)

@Composable
fun StSettingsBaseItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) = StSettingsBaseItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingContent = leading,
    trailingContent = trailingContent,
    enabled = enabled,
    onClick = onClick,
)

@Composable
internal fun StSettingsBaseItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIconPainter: Painter? = null,
    leadingIconImageVector: ImageVector? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val disableAlpha = SETTINGS_UI_DISABLE_ALPHA
    StBaseSettingsItem(
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
        trailingContent = trailingContent,
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
        } ?: leadingContent,
        onClick = if (enabled && onClick != null) {
            { onClick() }
        } else {
            null
        },
    )
}

@Composable
private fun StBaseSettingsItem(
    modifier: Modifier = Modifier,
    headlineContent: @Composable () -> Unit,
    leadingContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val boxModifier = onClick?.let {
        modifier.clickable { it() }
    } ?: modifier
    Box(
        modifier = boxModifier
    ) {
        StUiBaseListItem(
            headlineContent = headlineContent,
            leadingContent = leadingContent,
            supportingContent = supportingContent,
            trailingContent = trailingContent,
            overlineContent = overlineContent,
        )
    }
}

@StUiPreview
@Composable
private fun Preview() {
    StUiPreviewWrapper {

        StSettingsBaseItem(
            title = "This is the Title",
            subTitle = "This is the Subtitle",
            leadingIconPainter = null,
            leadingIconImageVector = Icons.Default.Settings,
            trailingContent = { Icon(Icons.AutoMirrored.Filled.NavigateNext, "trailing") },
            onClick = {},
        )

        StBaseSettingsItem(
            headlineContent = { Text("Headline Content") },
            leadingContent = { Text("Leading") },
            supportingContent = { Text("Supporting content") },
            trailingContent = { Text("Trailing Content") },
            overlineContent = { Text("overline Content") },
            onClick = {},
        )
    }
}
