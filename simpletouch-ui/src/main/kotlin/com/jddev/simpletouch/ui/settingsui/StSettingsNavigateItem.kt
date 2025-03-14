package com.jddev.simpletouch.ui.settingsui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.R
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper

@Composable
fun StSettingsNavigateItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) = StSettingsNavigateItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconImageVector = null,
    leadingIconPainter = null,
    enabled = enabled,
    onClick = onClick,
)

@Composable
fun StSettingsNavigateItem(
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    title: String,
    subTitle: String? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) = StSettingsNavigateItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconPainter = leadingIcon,
    enabled = enabled,
    onClick = onClick,
)

@Composable
fun StSettingsNavigateItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIcon: ImageVector? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) = StSettingsNavigateItem(
    modifier = modifier,
    title = title,
    subTitle = subTitle,
    leadingIconImageVector = leadingIcon,
    enabled = enabled,
    onClick = onClick,
)

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun StSettingsNavigateItem(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    leadingIconImageVector: ImageVector? = null,
    leadingIconPainter: Painter? = null,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val disableAlpha = SETTINGS_UI_DISABLE_ALPHA
    val uiStyle: StSettingsUiStyle = LocalStUiStyle.current

    val iconShape = when (uiStyle) {
        StSettingsUiStyle.Cupertino -> Icons.AutoMirrored.Filled.ArrowForwardIos
        else -> Icons.AutoMirrored.Filled.NavigateNext
    }
    val iconTrailingColor = when (uiStyle) {
        StSettingsUiStyle.Cupertino -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.onSurface
    }

    StSettingsBaseItem(
        modifier = modifier,
        title = title,
        subTitle = subTitle,
        leadingIconImageVector = leadingIconImageVector,
        leadingIconPainter = leadingIconPainter,
        trailingContent = {
            onClick?.let {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_ios_forward),
                    "navigate",
                    modifier = Modifier
                        .alpha(if (enabled) 1f else disableAlpha)
                        .height(18.dp),
                    tint = iconTrailingColor,
                )
            }
        },
        onClick = if (enabled) onClick else null,
    )
}

private fun StSettingsUiStyle.isIos() = this == StSettingsUiStyle.Cupertino

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        StSettingsUi {
            StSettingsNavigateItem(
                leadingIcon = Icons.Default.Settings,
                title = "Enable",
                subTitle = "Can navigate",
                enabled = true,
                onClick = { },
            )

            StSettingsNavigateItem(
                leadingIcon = Icons.Default.Language,
                title = "Enable",
                subTitle = "Can not navigate",
                enabled = true,
                onClick = null,
            )

            StSettingsNavigateItem(
                leadingIcon = Icons.Default.DateRange,
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
        StSettingsUi(
            uiStyle = StSettingsUiStyle.Cupertino,
        ) {
            StSettingsGroup {
                StSettingsNavigateItem(
                    title = "Cupertino",
                    subTitle = "Can navigate",
                    enabled = true,
                    onClick = { },
                )
            }
        }
    }
}
