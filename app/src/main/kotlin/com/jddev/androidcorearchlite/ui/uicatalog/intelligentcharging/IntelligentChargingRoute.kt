package com.jddev.androidcorearchlite.ui.uicatalog.intelligentcharging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.jddev.simpletouch.utils.permission.StUiCheckAndShowPermissionRequest

@Composable
fun IntelligentChargingRoute(
    intelligentChargingViewModel: IntelligentChargingViewModel = hiltViewModel(), onBack: () -> Unit
) {
    StUiCheckAndShowPermissionRequest(
        intelligentChargingRequiredPermission
    ) {
        val chargeUpToCapacityChecked =
            intelligentChargingViewModel.chargeUpToCapacityChecked.collectAsState()
        val chargeOnlyDisplayChecked =
            intelligentChargingViewModel.chargeOnlyDisplayChecked.collectAsState()

        IntelligentChargingScreen(
            chargeUpToCapacityChecked = chargeUpToCapacityChecked.value,
            chargeOnlyDisplayChecked = chargeOnlyDisplayChecked.value,
            chargeUpToCapacityChange = { intelligentChargingViewModel.chargeUpToCapacityChange(it) },
            chargeOnlyDisplayChange = { intelligentChargingViewModel.chargeOnlyDisplayChange(it) },
            onBack = onBack,
        )
    }
}