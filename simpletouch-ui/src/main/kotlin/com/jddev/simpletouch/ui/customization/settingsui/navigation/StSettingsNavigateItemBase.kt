package com.jddev.simpletouch.ui.customization.settingsui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsItemBase
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
internal fun StSettingsNavigateItemBase(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    StSettingsItemBase(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        onClick = if (enabled) onClick else null,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        StSettingsUi {
            StSettingsNavigateItemBase(
                leadingContent = { Icon(Icons.Default.Settings, null) },
                title = "Enable",
                subTitle = "Can navigate",
                enabled = true,
                onClick = { },
            )
        }
    }
}
