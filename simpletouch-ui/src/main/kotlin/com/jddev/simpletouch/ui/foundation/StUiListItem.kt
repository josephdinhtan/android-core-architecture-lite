package com.jddev.simpletouch.ui.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.headlineTextStyle
import com.jddev.simpletouch.ui.customization.settingsui.supportingTextStyle
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StUiListItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIconPainter: Painter? = null,
    leadingIconImageVector: ImageVector? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    StUiListItemBase(
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
                )
            }
        } ?: leadingIconPainter?.let {
            {
                Icon(
                    it,
                     "leading",
                )
            }
        } ?: leadingContent,
        onClick = onClick,
    )
}

@Composable
internal fun StUiListItemBase(
    modifier: Modifier = Modifier,
    headlineContent: @Composable () -> Unit,
    leadingContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val boxModifier = onClick?.let {
        modifier.clickable { it() }
    } ?: modifier
    Box(
        modifier = boxModifier
    ) {
        Row(
            Modifier.padding(
                top = StSettingsUi.dimension.itemVerticalPadding,
                bottom = StSettingsUi.dimension.itemVerticalPadding,
                start = StSettingsUi.dimension.itemStartPadding,
                end = StSettingsUi.dimension.itemEndPadding
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingContent?.let {
                Box(
                    Modifier.padding(
                        start = 2.dp, // additional padding for icon
                        end = StSettingsUi.dimension.itemStartPadding
                    )
                ) {
                    it()
                }
            }
            Column(
                Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                headlineContent()
                supportingContent?.let {
                    Box(Modifier.padding(top = 2.dp)) {
                        it()
                    }
                }
            }
            trailingContent?.let {
                Box(Modifier.padding(start = StSettingsUi.dimension.itemEndPadding)) {
                    it()
                }
            }
        }
//        ListItem(
//            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
//            headlineContent = headlineContent,
//            leadingContent = leadingContent,
//            supportingContent = supportingContent,
//            trailingContent = trailingContent,
//            overlineContent = overlineContent,
//        )
    }
}

@StUiPreview
@Composable
private fun Preview() {
    StUiPreviewWrapper {
        ListItem(
            headlineContent = { Text("Headline Content") },
            leadingContent = { Text("Leading") },
            supportingContent = { Text("Supporting content") },
            trailingContent = { Text("Trailing Content") },
            overlineContent = { Text("Overline Content") },
        )

        StUiListItemBase(
            headlineContent = { Text("Headline Content") },
            leadingContent = { Text("Leading") },
            supportingContent = { Text("Supporting content") },
            trailingContent = { Text("Trailing Content") },
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

