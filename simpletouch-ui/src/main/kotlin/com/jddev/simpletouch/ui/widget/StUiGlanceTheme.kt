package com.jddev.simpletouch.ui.widget

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceTheme
import androidx.glance.material3.ColorProviders
import com.jddev.simpletouch.ui.theme.standard.StandardDarkColors
import com.jddev.simpletouch.ui.theme.standard.StandardLightColors

@Composable
fun StUiGlanceTheme(
    useDynamicColors: Boolean = false,
    backgroundAlpha: Float = 1f,
    content: @GlanceComposable @Composable () -> Unit
) {
    val glanceLightColor =
        StandardLightColors.copy(background = StandardLightColors.background.copy(alpha = backgroundAlpha))
    val glanceDarkColor =
        StandardDarkColors.copy(background = StandardDarkColors.background.copy(alpha = backgroundAlpha))
    GlanceTheme(
        colors = when (useDynamicColors) {
            true -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                GlanceTheme.colors
            } else {
                ColorProviders(glanceLightColor, glanceDarkColor)
            }

            false -> ColorProviders(glanceLightColor, glanceDarkColor)
        }, content = content
    )
}
