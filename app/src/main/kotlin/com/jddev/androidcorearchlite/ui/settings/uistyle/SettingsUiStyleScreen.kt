package com.jddev.androidcorearchlite.ui.settings.uistyle

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsUiStyleScreen(
    settingsUiStyle: StSettingsUiStyle,
    onBack: () -> Unit,
    onUiStyleChange: (StSettingsUiStyle) -> Unit,
) {
    Scaffold(topBar = {
        StUiTopAppBar(
            title = "UI Style",
            onBack = onBack,
        )
    }) {
        // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
        StSettingsUi(
            Modifier
                .padding(it)
                .selectableGroup(), uiStyle = settingsUiStyle
        ) {
            StSettingsGroup {
                SettingsUiStyleContent(settingsUiStyle, onUiStyleChange)
            }
        }
    }
}

@Composable
fun SettingsUiStyleContent(
    settingsUiStyle: StSettingsUiStyle,
    onUiStyleChange: (StSettingsUiStyle) -> Unit,
) {
    val radioOptions = listOf(
        Pair("Material", StSettingsUiStyle.Material),
        Pair("Cupertino", StSettingsUiStyle.Cupertino),
    )
    var selectedOption by remember { mutableStateOf(settingsUiStyle) }
    radioOptions.forEach { themeModeOption ->
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectable(
                    selected = (themeModeOption.second == selectedOption), onClick = {
                        selectedOption = themeModeOption.second
                        onUiStyleChange(themeModeOption.second)
                    }, role = Role.RadioButton
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = (themeModeOption.second == selectedOption), onClick = null
            )
            Text(
                text = themeModeOption.first,
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}