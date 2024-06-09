package com.jddev.designsystem.settingsui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SettingsItem(
    modifier: Modifier = Modifier,
    headlineContent: @Composable () -> Unit,
    leadingContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier =
            onClick?.let {
                modifier.clickable{ it() }
            } ?: modifier
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

@Preview
@Composable
private fun preview() {
    SettingsItem(
        headlineContent = { Text("Headline Content") },
        leadingContent = { Text("Leading") },
        supportingContent = { Text("Supporting content") },
        trailingContent = { Text("Trailing Content") },
        overlineContent = { Text("overline Content") },
        onClick = {},
    )
}
