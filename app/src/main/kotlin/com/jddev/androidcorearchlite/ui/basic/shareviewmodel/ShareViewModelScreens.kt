package com.jddev.androidcorearchlite.ui.basic.shareviewmodel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsBaseItem
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareViewModelMainScreen(
    viewModel: ShareViewModelViewModel,
    onBack: () -> Unit,
    navigateToLevel1: () -> Unit,
) {
    val countValue = viewModel.count.collectAsState()
    StUiSimpleScaffold(
        title = "Share ViewModel",
        onBack = onBack,
    ) {
        Text("Count: ${countValue.value}", modifier = Modifier.padding(16.dp))
        StSettingsUi(uiStyle = StSettingsUiStyle.Cupertino) {
            StSettingsGroup {
                StSettingsBaseItem(title = "Increment", onClick = { viewModel.increaseCount() })
                StSettingsBaseItem(title = "Decrement", onClick = { viewModel.decreaseCount() })
            }
        }
        StSettingsUi(uiStyle = StSettingsUiStyle.Cupertino) {
            StSettingsGroup {
                StSettingsBaseItem(title = "Navigate to level 1", onClick = navigateToLevel1)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareViewModelScreenLevel1(
    viewModel: ShareViewModelViewModel,
    onBack: () -> Unit,
    navigateToLevel2: () -> Unit,
) {
    val countValue = viewModel.count.collectAsState()
    StUiSimpleScaffold(
        title = "Share ViewModel level1",
        onBack = onBack,
    ) {
        Text("Count: ${countValue.value}", modifier = Modifier.padding(16.dp))
        Spacer(Modifier.height(24.dp))
        StSettingsUi(uiStyle = StSettingsUiStyle.Cupertino) {
            StSettingsGroup {
                StSettingsBaseItem(title = "Increment", onClick = { viewModel.increaseCount() })
                StSettingsBaseItem(title = "Decrement", onClick = { viewModel.decreaseCount() })
            }
        }
        StSettingsUi(uiStyle = StSettingsUiStyle.Cupertino) {
            StSettingsGroup {
                StSettingsBaseItem(title = "Navigate to level 2", onClick = navigateToLevel2)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareViewModelScreenLevel2(
    viewModel: ShareViewModelViewModel,
    onBack: () -> Unit,
) {
    val countValue = viewModel.count.collectAsState()
    StUiSimpleScaffold(
        title = "Share ViewModel level2",
        onBack = onBack,
    ) {
        Text("Count: ${countValue.value}", modifier = Modifier.padding(16.dp))
        Spacer(Modifier.height(24.dp))
        StSettingsUi(uiStyle = StSettingsUiStyle.Cupertino) {
            StSettingsGroup {
                StSettingsBaseItem(title = "Increment", onClick = { viewModel.increaseCount() })
                StSettingsBaseItem(title = "Decrement", onClick = { viewModel.decreaseCount() })
            }
        }
    }
}