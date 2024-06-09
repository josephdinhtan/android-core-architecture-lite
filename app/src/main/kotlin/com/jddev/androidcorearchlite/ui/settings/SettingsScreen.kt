package com.jddev.androidcorearchlite.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.jddev.designsystem.component.CoreArchScrollBehavior
import com.jddev.designsystem.component.CoreArchTopAppBar
import com.jddev.designsystem.settingsui.SettingsGroup
import com.jddev.designsystem.settingsui.SettingsNavigateItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier,
    onThemeChange: () -> Unit,
    onBack: () -> Unit,
) {
    val scrollBehavior = CoreArchScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            CoreArchTopAppBar(
                modifier = modifier,
                scrollBehavior = scrollBehavior,
                onBack = onBack,
                title = "Settings",
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {
            item {
                SettingsGroup(title = "General") {
                    SettingsNavigateItem(
                        title = "Theme",
                        subTitle = "Dark mode",
                        leadingIcon = Icons.Default.DarkMode,
                        onClick = onThemeChange,
                    )
                    SettingsNavigateItem(
                        title = "Language",
                        subTitle = "English",
                        leadingIcon = Icons.Default.Language,
                        onClick = onThemeChange,
                    )
                }
            }
            item {
                SettingsGroup(title = "Support & Feedback") {
                    SettingsNavigateItem(
                        title = "Report an issue",
                        leadingIcon = Icons.Default.Flag,
                        onClick = onThemeChange,
                    )
                    SettingsNavigateItem(
                        title = "Chat with us",
                        leadingIcon = Icons.Filled.Chat,
                        onClick = onThemeChange,
                    )
                    SettingsNavigateItem(
                        title = "About us",
                        leadingIcon = Icons.Default.Info,
                        onClick = onThemeChange,
                    )
                }
            }
        }
    }
}