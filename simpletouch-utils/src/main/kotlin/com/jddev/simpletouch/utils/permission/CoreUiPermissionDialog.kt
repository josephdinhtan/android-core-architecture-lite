package com.jddev.simpletouch.utils.permission

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jddev.simpletouch.ui.foundation.StUiCameraCommonDialog

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckAndShowPermissionRequestDialog(
    androidVersion: Int = android.os.Build.VERSION.SDK_INT,
    content: @Composable () -> Unit,
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(
        getPermissionRequired(androidVersion)
    )
    Box {
        content()
        // If all permissions are granted, then show screen with the feature enabled
        if (!multiplePermissionsState.allPermissionsGranted) {
            StUiCameraCommonDialog(
                title = "Need Permissions",
                message = getTextToShowGivenPermissions(),
                onConfirmRequest = {
                    multiplePermissionsState.launchMultiplePermissionRequest()
                },
            )
        }
    }
}

private fun getTextToShowGivenPermissions(): String {
    return "Please grant all permissions for the app to function properly."
}
