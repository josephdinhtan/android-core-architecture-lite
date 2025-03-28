package com.jddev.simpletouch.ui.customization.settingsui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.jddev.simpletouch.ui.customization.settingsui.checkbox.StSettingsCheckBoxItem
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StSettingsUi(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    stSettingsUiColors: StSettingsUiColors = StSettingsUiColors.Default,
    stSettingsUiDimension: StSettingsDimension = StSettingsDimension.Default,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalStSettingsUiColor provides stSettingsUiColors,
        LocalStSettingsUiDimension provides stSettingsUiDimension
    ) {
        scrollBehavior?.let {
            LazyColumn(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            ) {
                item {
                    content()
                }
            }
        } ?: Column(modifier = modifier) { content() }
    }
}

object StSettingsUi {
    val colors: StSettingsUiColors
        @Composable @ReadOnlyComposable get() = LocalStSettingsUiColor.current

    val dimension: StSettingsDimension
        @Composable @ReadOnlyComposable get() = LocalStSettingsUiDimension.current
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    var checkBoxState by remember { mutableStateOf(true) }
    var toggleSwitchState by remember { mutableStateOf(true) }
    val subTitle = "Turn this feature on to do something"
    StUiPreviewWrapper {
        StSettingsUi {
            StSettingsGroup(
                header = "Group Title 1",
                content = {
                    StSettingsCheckBoxItem(
                        leadingImageVector = Icons.Default.Home,
                        title = "Check Box",
                        subTitle = subTitle,
                        checked = checkBoxState,
                        onCheckedChange = { checkBoxState = it },
                    )
                    StSettingsSwitchItem(
                        leadingImageVector = Icons.Default.Hub,
                        title = "Toggle Switch",
                        subTitle = subTitle,
                        checked = toggleSwitchState,
                        onCheckedChange = { toggleSwitchState = it },
                    )
                    StSettingsNavigateItem(
                        leadingImageVector = Icons.Default.Headphones,
                        title = "Navigate Item",
                        subTitle = subTitle,
                        onClick = {},
                    )
                },
            )
        }

        StSettingsUi {
            StSettingsGroup(
                header = "Group Title 2",
                content = {
                    StSettingsCheckBoxItem(
                        leadingImageVector = Icons.Default.Home,
                        title = "Check Box",
                        subTitle = subTitle,
                        checked = checkBoxState,
                        onCheckedChange = { checkBoxState = it },
                    )
                    StSettingsSwitchItem(
                        leadingImageVector = Icons.Default.Hub,
                        title = "Toggle Switch",
                        subTitle = subTitle,
                        checked = toggleSwitchState,
                        onCheckedChange = { toggleSwitchState = it },
                    )
                    StSettingsNavigateItem(
                        leadingImageVector = Icons.Default.Headphones,
                        title = "Navigate Item",
                        subTitle = subTitle,
                        onClick = {},
                    )
                },
            )
        }
    }
}
