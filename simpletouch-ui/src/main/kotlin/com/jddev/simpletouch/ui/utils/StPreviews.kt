package com.jddev.simpletouch.ui.utils

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jddev.simpletouch.ui.theme.StUiTheme

@Preview(
    name = "Light theme", uiMode = Configuration.UI_MODE_NIGHT_NO,
//    widthDp = 480,
//    heightDp = 800,
    device = Devices.PIXEL_3
)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    widthDp = 480,
//    heightDp = 800,
    device = Devices.PIXEL_3,
)
annotation class StUiPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StUiPreviewWrapper(
    fullScreen: Boolean = false,
    showBackground: Boolean = true,
    content: @Composable () -> Unit
) {
    StUiTheme {
        if (fullScreen) {
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("St Preview") })
                },
                containerColor = if (showBackground)
                    MaterialTheme.colorScheme.background
                else
                    Color.Transparent
            ) {
                Column(
                    modifier = Modifier.padding(it),
                ) {
                    content()
                }
            }
        } else {
            Surface(
                color = if (showBackground)
                    MaterialTheme.colorScheme.background
                else
                    Color.Transparent
            ) {
                Column(modifier = Modifier.wrapContentHeight()) {
                    content()
                }
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        Text("This is a demo preview")
    }
}