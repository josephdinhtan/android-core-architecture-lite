package com.jddev.simpletouch.ui.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jddev.simpletouch.ui.customization.settingsui.headlineTextStyle
import com.jddev.simpletouch.ui.customization.settingsui.supportingTextStyle

private val ADDITIONAL_LEADING_ICON_PADDING_LIST_ITEM = 8.dp

@Composable
fun StUiListItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leading: Painter? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    StUiListItem(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconPainter = leading,
        leadingIconImageVector = null,
        leadingContent = null,
        trailingContent = trailingContent,
        onClick = onClick,
    )
}

@Composable
fun StUiListItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leading: ImageVector? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    StUiListItem(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconPainter = null,
        leadingIconImageVector = leading,
        leadingContent = null,
        trailingContent = trailingContent,
        onClick = onClick,
    )
}

@Composable
fun StUiListItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    StUiListItem(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconPainter = null,
        leadingIconImageVector = null,
        leadingContent = leading,
        trailingContent = trailingContent,
        onClick = onClick
    )
}

@Composable
internal fun StUiListItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIconPainter: Painter? = null,
    leadingIconImageVector: ImageVector? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    StUiBaseListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                title,
                style = headlineTextStyle,
            )
        },
        supportingContent = {
            subTitle?.let {
                Text(
                    it,
                    style = supportingTextStyle,
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
                        .padding(start = ADDITIONAL_LEADING_ICON_PADDING_LIST_ITEM)
                )
            }
        } ?: leadingIconPainter?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier
                        .padding(start = ADDITIONAL_LEADING_ICON_PADDING_LIST_ITEM)
                )
            }
        } ?: leadingContent,
        onClick = onClick,
    )
}

@Composable
internal fun StUiBaseListItem(
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
        ListItem(
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
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

        StUiBaseListItem(
            headlineContent = { Text("Headline Content") },
            leadingContent = { Text("Leading") },
            supportingContent = { Text("Supporting content") },
            trailingContent = { Text("Trailing Content") },
            overlineContent = { Text("overline Content") },
            onClick = {},
        )

        StUiListItem(
            title = "Photos",
            subTitle = "Jan 9 - 2025",
            leadingContent = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                ) {
                    Icon(
                        Icons.Default.Folder, "leading",
                        tint = Color.LightGray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            trailingContent = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Info, "trailing")
                }
            },
            onClick = {}
        )
        PreviewTestItem()
        PreviewTestItem()
    }
}

@Composable
private fun PreviewTestItem() {
    StUiListItem(
        title = "Headline",
        subTitle = "Supporting text",
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    "A",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                )
            }
        },
        trailingContent = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.FavoriteBorder, "trailing")
            }
        },
        onClick = {}
    )
}

