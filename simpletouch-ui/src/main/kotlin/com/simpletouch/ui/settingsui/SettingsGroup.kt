package com.simpletouch.ui.settingsui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsGroup(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                bottom = 8.dp,
            ),
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = MaterialTheme.shapes.large,
        ) {
            content()
        }
    }
}

@Composable
@Preview
private fun preview() {
    MaterialTheme {
        SettingsGroup(
            title = "Group Title",
            content = {
                SettingsCheckBoxItem(
                    leadingIcon = Icons.Default.DarkMode,
                    title = "Dark mode",
                    subTitle = "Enable dark mode",
                    checked = true,
                    onClick = {},
                )
                SettingsSwitchItem(
                    leadingIcon = Icons.Default.DarkMode,
                    title = "Dark mode",
                    subTitle = "Enable dark mode",
                    isChecked = true,
                    onClick = {},
                )
                SettingsNavigateItem(
                    leadingIcon = Icons.Default.Language,
                    title = "Language",
                    subTitle = "English",
                    enabled = false,
                    onClick = {},
                )
            },
        )
    }
}
