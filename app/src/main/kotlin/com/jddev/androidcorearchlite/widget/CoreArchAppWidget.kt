package com.jddev.androidcorearchlite.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import androidx.glance.text.TextDefaults.defaultTextStyle
import com.jddev.androidcorearchlite.R
import com.jddev.androidcorearchlite.ui.MainActivity
import com.jddev.simpletouch.ui.widget.StUiGlanceTheme

class CoreArchAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            StUiGlanceTheme (
                backgroundAlpha = 0.7f
            ) {
                Content()
            }
        }
    }

    @Composable
    private fun Content() {
        Scaffold(
            titleBar = {
                TitleBar(
                    modifier = GlanceModifier.clickable(actionStartActivity(MainActivity::class.java)),
                    textColor = GlanceTheme.colors.onSurface,
                    startIcon = ImageProvider(R.drawable.ic_launcher_foreground),
                    title = "CoreArch",
                )
            },
            backgroundColor = GlanceTheme.colors.background,
            modifier = GlanceModifier.fillMaxSize(),
        ) {
            Box(modifier = GlanceModifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Hello, I'm CoreArch",
                    style = defaultTextStyle.copy(color = GlanceTheme.colors.onSurface)
                )
            }
        }
    }
}