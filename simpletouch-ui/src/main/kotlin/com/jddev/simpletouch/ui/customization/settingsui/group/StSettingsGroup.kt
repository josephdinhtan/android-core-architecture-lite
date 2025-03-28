package com.jddev.simpletouch.ui.customization.settingsui.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.outlined.ChatBubbleOutline
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.checkbox.StSettingsCheckBoxItem
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StSettingsGroup(
    modifier: Modifier = Modifier,
    header: String? = null,
    headerTextStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.W600
    ),
    containerColor: Color = Color.Transparent,
    shape: Shape = MaterialTheme.shapes.small,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 4.dp),
    ) {
        header?.let {
            Text(
                text = it,
                style = headerTextStyle,
                modifier = Modifier.padding(
                    start = StSettingsUi.dimension.itemStartPadding,
                    end = StSettingsUi.dimension.itemEndPadding,
                    top = 16.dp,
                    bottom = 8.dp,
                ),
            )
        }

        Card(
            modifier = Modifier.padding(horizontal = 0.dp),
            colors = CardDefaults.cardColors(containerColor = containerColor),
            shape = shape,
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
        StSettingsUi {
            StSettingsGroup(
                header = "Group Title",
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
        StSettingsGroup(
            header = "Group Title - No Icon",
            content = {
                StSettingsCheckBoxItem(
                    title = "Check Box",
                    leadingImageVector = Icons.Outlined.ChatBubbleOutline,
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
