package com.jddev.simpletouch.ui.foundation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.icon.StUiIcons
import com.jddev.simpletouch.ui.settingsui.LocalStUiStyle
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StUiTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    colors: TopAppBarColors = TopAppBarDefaults.largeTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
    ),
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    onBack: (() -> Unit)? = null,
) {
    val topAppBarColors = when (LocalStUiStyle.current) {
        StSettingsUiStyle.Cupertino -> TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
        )
        else -> colors
    }
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = topAppBarColors,
        title = { Text(text = title) },
        navigationIcon = {
            onBack?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = StUiIcons.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        actions = actions,
    )
}
