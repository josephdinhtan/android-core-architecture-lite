package com.jddev.simpletouch.ui.settingsui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper

@Composable
fun StSettingsGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable ColumnScope.() -> Unit,
) {
    val localUiStyle = LocalStUiStyle.current
    Column(
        modifier = modifier.padding(vertical = 4.dp),
    ) {
        if (title != null) Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(color = titleColor),
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 8.dp,
            ),
        )
        val cardColor = when (localUiStyle) {
            StSettingsUiStyle.Cupertino, StSettingsUiStyle.OneUi -> MaterialTheme.colorScheme.surface
            StSettingsUiStyle.Material -> Color.Transparent
        }
        val cardPadding = when (localUiStyle) {
            StSettingsUiStyle.Cupertino -> 16.dp
            StSettingsUiStyle.Material, StSettingsUiStyle.OneUi -> 0.dp
        }
        val cardShape = when (localUiStyle) {
            StSettingsUiStyle.Cupertino -> MaterialTheme.shapes.large
            StSettingsUiStyle.Material -> CardDefaults.shape
            StSettingsUiStyle.OneUi -> MaterialTheme.shapes.extraLarge

        }
        Card(
            modifier = Modifier.padding(horizontal = cardPadding),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            shape = cardShape,
        ) {
            content()
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    var checkBoxState by remember { mutableStateOf(true) }
    var toggleSwitchState by remember { mutableStateOf(true) }
    val subTitle = "Turn this feature on to do something"
    StUiPreviewWrapper {
        StSettingsGroup(
            title = "Group Title",
            content = {
                StSettingsCheckBoxItem(
                    leadingIcon = Icons.Default.Home,
                    title = "Check Box",
                    subTitle = subTitle,
                    checked = checkBoxState,
                    onCheckedChange = { checkBoxState = it },
                )
                StSettingsSwitchItem(
                    leadingIcon = Icons.Default.Hub,
                    title = "Toggle Switch",
                    subTitle = subTitle,
                    checked = toggleSwitchState,
                    onCheckedChange = { toggleSwitchState = it },
                )
                StSettingsNavigateItem(
                    leadingIcon = Icons.Default.Headphones,
                    title = "Navigate Item",
                    subTitle = subTitle,
                    onClick = {},
                )
            },
        )
        StSettingsGroup(
            title = "Group Title - No Icon",
            content = {
                StSettingsCheckBoxItem(
                    title = "Check Box",
                    subTitle = subTitle,
                    checked = checkBoxState,
                    onCheckedChange = { checkBoxState = it },
                )
                StSettingsSwitchItem(
                    title = "Toggle Switch",
                    subTitle = subTitle,
                    checked = toggleSwitchState,
                    onCheckedChange = { toggleSwitchState = it },
                )
                StSettingsNavigateItem(
                    title = "Navigate Item",
                    subTitle = subTitle,
                    onClick = {},
                )
            },
        )
    }
}
