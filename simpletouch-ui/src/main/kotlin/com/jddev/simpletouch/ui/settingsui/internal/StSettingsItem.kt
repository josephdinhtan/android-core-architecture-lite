package com.jddev.simpletouch.ui.settingsui.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper

@Composable
internal fun StSettingsItem(
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
        StSettingsItem(
            headlineContent = { Text("Headline Content") },
            leadingContent = { Text("Leading") },
            supportingContent = { Text("Supporting content") },
            trailingContent = { Text("Trailing Content") },
            overlineContent = { Text("overline Content") },
            onClick = {},
        )
    }
}
