package com.jddev.androidcorearchlite.ui.samepleui.intelligentcharging

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jddev.androidcorearchlite.R
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUiStyle
import com.jddev.simpletouch.ui.customization.settingsui.switch.StSettingsSwitchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntelligentChargingScreen(
    onBack: () -> Unit,
    chargeUpToCapacityChecked: Boolean,
    chargeOnlyDisplayChecked: Boolean,
    chargeUpToCapacityChange: (checked: Boolean) -> Unit,
    chargeOnlyDisplayChange: (checked: Boolean) -> Unit,
) {
    Scaffold(topBar = {
        StUiTopAppBar(
            title = "Intelligent Charging", colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ), onBack = onBack
        )
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Image(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    painter = painterResource(R.drawable.img_intelligentcharge),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primaryContainer,
                                        MaterialTheme.colorScheme.background
                                    )
                                )
                            )
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 20.dp),
                        style = MaterialTheme.typography.labelLarge,
                        text = "Extend battery life with smart auto-charge control and direct power supply settings"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary),
                    text = "Additional settings to reduce battery load."
                )
                Spacer(modifier = Modifier.height(16.dp))
                StSettingsUi(
                    modifier = Modifier.wrapContentHeight(), uiStyle = StSettingsUiStyle.Material
                ) {
                    StSettingsGroup {
                        StSettingsSwitchItem(
                            title = "Charge up to the specified capacity",
                            subTitle = "Switch to direct power supply when the battery is about 90% charged",
                            checked = chargeUpToCapacityChecked,
                            onCheckedChange = chargeUpToCapacityChange
                        )
                        StSettingsSwitchItem(
                            title = "Charge only when screen is off",
                            subTitle = "Charge the battery while the screen is off and switch to direct power supply when the screen is on",
                            checked = chargeOnlyDisplayChecked,
                            onCheckedChange = chargeOnlyDisplayChange
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Icon(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Infomation"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),


                    style = MaterialTheme.typography.labelLarge,
                    text = "Direct power supply is a method that provides power directly from the charger without charging the battery.   This helps reduce the strain on the battery caused by heat or charging near full capacity."
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.labelLarge,
                    text = "Regardless of the direct power supply settings, the battery may be charged to ensure stable operation."
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        IntelligentChargingScreen(onBack = {}, false, false, {}, {})
    }
}