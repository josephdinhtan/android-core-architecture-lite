package com.jddev.simpletouch.ui.customization.settingsui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StSettingsNavigateItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    value: String? = null,
    leadingImageVector: ImageVector? = null,
    leadingIconPainter: Painter? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    StSettingsNavigateItemBase(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingContent = leadingImageVector?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier.alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingIconPainter?.let {
            {
                Icon(
                    it,
                    "leading",
                    modifier = Modifier.alpha(if (enabled) 1f else StSettingsUi.colors.disableAlpha),
                )
            }
        } ?: leadingContent,
//        trailingContent = {
//            Icon(
//                painter = painterResource(R.drawable.ic_arrow_ios_forward),
//                "navigate",
//                modifier = Modifier.height(18.dp),
//            )
//        },
        onClick = if (enabled) onClick else null,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        StSettingsUi {
            StSettingsNavigateItem(
                leadingImageVector = Icons.Default.Settings,
                title = "Enable",
                subTitle = "Can navigate",
                enabled = true,
                onClick = { },
            )

            StSettingsNavigateItem(
                leadingImageVector = Icons.Default.Language,
                title = "Enable",
                subTitle = "Can not navigate",
                enabled = true,
                onClick = null,
            )

            StSettingsNavigateItem(
                leadingImageVector = Icons.Default.DateRange,
                title = "Disable",
                subTitle = "Can not navigate",
                enabled = false,
                onClick = { },
            )

            StSettingsNavigateItem(
                title = "Disable",
                subTitle = "Can not navigate",
                enabled = false,
                onClick = { },
            )
        }
    }
}
