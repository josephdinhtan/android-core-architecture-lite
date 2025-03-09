package com.jddev.androidcorearchlite.ui.samepleui.intelligentcharging

import android.Manifest

val intelligentChargingRequiredPermission = when (android.os.Build.VERSION.SDK_INT) {
    android.os.Build.VERSION_CODES.TIRAMISU, android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE, android.os.Build.VERSION_CODES.VANILLA_ICE_CREAM -> listOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    else -> listOf()
}