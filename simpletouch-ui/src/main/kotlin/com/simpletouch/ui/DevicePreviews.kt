package com.simpletouch.ui

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    widthDp = 480,
//    heightDp = 800,
    device = Devices.PIXEL_3
)
@Preview(
    name = "Dark theme",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    widthDp = 480,
//    heightDp = 800,
    device = Devices.PIXEL_3
)
annotation class DevicePreviews
