package com.jddev.androidcorearchlite.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.jddev.androidcorearchlite.CoreArchApplication
import com.jddev.androidcorearchlite.ui.debug.DevControlPanelContent
import com.jddev.simpletouch.utils.debugui.DevUtilityUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var appContainer: com.jddev.androidcorearchlite.AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appContainer = (application as CoreArchApplication).container
        setContent {
            if (appContainer.appConfig.isDebugMode()) {
                DevUtilityUi(
                    modifier = Modifier.fillMaxSize(),
                    isEnable = true,
                    logManager = appContainer.devUtility.logManager,
                    devControlPanelContent = { DevControlPanelContent(appContainer.devUtility) }
                ) {
                    CoreArchApp(appContainer = appContainer)
                }

            } else {
                CoreArchApp(appContainer = appContainer)
            }
        }
    }
}