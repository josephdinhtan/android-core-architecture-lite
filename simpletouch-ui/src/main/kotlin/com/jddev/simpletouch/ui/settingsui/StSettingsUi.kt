package com.jddev.simpletouch.ui.settingsui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StSettingsUi(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    uiStyle: StSettingsUiStyle = StSettingsUiStyle.Material,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalStUiStyle provides uiStyle) {
        scrollBehavior?.let {
            LazyColumn(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            ) {
                item {
                    content()
                }
            }
        } ?: Column(modifier = modifier.verticalScroll(rememberScrollState())) { content() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    var checkBoxState by remember { mutableStateOf(true) }
    var toggleSwitchState by remember { mutableStateOf(true) }
    val subTitle = "Turn this feature on to do something"
    StUiPreviewWrapper {
        StSettingsUi(
            uiStyle = StSettingsUiStyle.Cupertino,
        ) {
            StSettingsGroup(
                title = "Group Title 1",
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
        }

        StSettingsUi(
            uiStyle = StSettingsUiStyle.Material,
        ) {
            StSettingsGroup(
                title = "Group Title 2",
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
        }
    }
}
