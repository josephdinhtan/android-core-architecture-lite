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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.jddev.simpletouch.ui.StUi
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.settingsui.checkbox.StSettingsCheckBoxItem
import com.jddev.simpletouch.ui.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.settingsui.switch.StSettingsSwitchItem
import java.util.Locale

@Composable
fun StSettingsGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable ColumnScope.() -> Unit,
) {
    val uiStyle = StUi.settingsUiStyle
    Column(
        modifier = modifier.padding(vertical = 4.dp),
    ) {
        val headerTextStyle = when (uiStyle) {
            StSettingsUiStyle.Cupertino -> groupTableTextStyle
            else -> MaterialTheme.typography.titleMedium.copy(color = titleColor)
        }
        val headerText: String? = when (uiStyle) {
            StSettingsUiStyle.Cupertino -> title?.uppercase(Locale.getDefault())
            else -> title
        }
        val headerPaddingStart = when (uiStyle) {
            StSettingsUiStyle.Cupertino -> 32.dp
            else -> 16.dp
        }

        headerText?.let {
            Text(
                text = it,
                style = headerTextStyle,
                modifier = Modifier.padding(
                    start = headerPaddingStart,
                    top = 16.dp,
                    bottom = 8.dp,
                ),
            )
        }
        val cardColor = when (uiStyle) {
            StSettingsUiStyle.Cupertino, StSettingsUiStyle.OneUi -> MaterialTheme.colorScheme.surface
            StSettingsUiStyle.Material -> Color.Transparent
        }
        val cardPadding = when (uiStyle) {
            StSettingsUiStyle.Cupertino -> 16.dp
            StSettingsUiStyle.Material, StSettingsUiStyle.OneUi -> 0.dp
        }
        val cardShape = when (uiStyle) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@StUiPreview
private fun Preview() {
    var checkBoxState by remember { mutableStateOf(true) }
    var toggleSwitchState by remember { mutableStateOf(true) }
    val subTitle = "Turn this feature on to do something"
    StUiPreviewWrapper {
        StSettingsUi(uiStyle = StSettingsUiStyle.Cupertino) {
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
        }
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
