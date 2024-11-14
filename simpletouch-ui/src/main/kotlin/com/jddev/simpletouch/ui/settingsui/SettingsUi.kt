package com.jddev.simpletouch.ui.settingsui

import androidx.annotation.NonNull
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsUi(
    modifier: Modifier = Modifier,
    uiStyle: UiStyle = UiStyle.Material,
    content: LazyListScope.() -> Unit
) {
    CompositionLocalProvider(LocalUiStyle provides uiStyle) {
        LazyColumn(
            modifier = modifier,
        ) {
            content()
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MaterialTheme {
        SettingsUi(
            modifier = Modifier.fillMaxSize(),
            uiStyle = UiStyle.Cupertino,
        ) {
            item {
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
    }
}
