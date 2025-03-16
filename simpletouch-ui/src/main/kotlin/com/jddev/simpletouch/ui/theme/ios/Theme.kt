package com.jddev.simpletouch.ui.theme.ios

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.jddev.simpletouch.ui.theme.standard.StUiShapes
import com.jddev.simpletouch.ui.theme.standard.StUiTypography
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_error
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_errorContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_inverseOnSurface
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_inversePrimary
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_inverseSurface
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onError
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onErrorContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onPrimary
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onPrimaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onSecondary
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onSecondaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onTertiary
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_onTertiaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_outline
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_outlineVariant
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_primaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_scrim
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_secondaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_surfaceTint
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_surfaceVariant
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_surface_container
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_surface_container_highest
import com.jddev.simpletouch.ui.theme.standard.md_theme_dark_tertiaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_error
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_errorContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_inverseOnSurface
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_inversePrimary
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_inverseSurface
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onError
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onErrorContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onPrimary
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onPrimaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onSecondary
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onSecondaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onTertiary
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_onTertiaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_outline
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_outlineVariant
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_primaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_scrim
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_secondaryContainer
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_surfaceTint
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_surfaceVariant
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_surface_container
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_surface_container_highest
import com.jddev.simpletouch.ui.theme.standard.md_theme_light_tertiaryContainer

private val LightColors = lightColorScheme(
    primary = ios_theme_light_blue,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = ios_theme_light_green,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = ios_theme_light_tertiary_label,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = ios_theme_light_tertiary_grouped_background,
    onBackground = ios_theme_light_primary_label,
    surface = ios_theme_light_secondary_grouped_background,
    surfaceContainer = md_theme_light_surface_container,
    surfaceContainerHighest = md_theme_light_surface_container_highest,
    onSurface = ios_theme_light_primary_label,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = ios_theme_light_secondary_label,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = ios_theme_dark_blue,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = ios_theme_dark_green,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = ios_theme_dark_tertiary_label,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = ios_theme_dark_grouped_background,
    onBackground = ios_theme_dark_primary_label,
    surface = ios_theme_dark_secondary_grouped_background,
    onSurface = ios_theme_dark_primary_label,
    surfaceContainer = md_theme_dark_surface_container,
    surfaceContainerHighest = md_theme_dark_surface_container_highest,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = ios_theme_dark_secondary_label,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

@Composable
fun IosTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = StUiShapes,
        typography = StUiTypography,
        content = content
    )
}
