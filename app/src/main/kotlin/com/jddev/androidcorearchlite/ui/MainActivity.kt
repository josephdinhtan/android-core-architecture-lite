package com.jddev.androidcorearchlite.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jddev.androidcorearchlite.app.AppContainer
import com.jddev.androidcorearchlite.app.CoreArchApplication

class MainActivity : ComponentActivity() {
    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appContainer = (application as CoreArchApplication).container
        setContent {
            CoreArchApp(appContainer = appContainer)
        }
    }
}