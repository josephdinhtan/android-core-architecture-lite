package com.jddev.simpletouch.ui.theme.sh

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = sh_theme_light_primary,
    onPrimary = sh_theme_light_onPrimary,
    primaryContainer = sh_theme_light_primaryContainer,
    onPrimaryContainer = sh_theme_light_onPrimaryContainer,
    secondary = sh_theme_light_secondary,
    onSecondary = sh_theme_light_onSecondary,
    secondaryContainer = sh_theme_light_secondaryContainer,
    onSecondaryContainer = sh_theme_light_onSecondaryContainer,
    tertiary = sh_theme_light_tertiary,
    onTertiary = sh_theme_light_onTertiary,
    tertiaryContainer = sh_theme_light_tertiaryContainer,
    onTertiaryContainer = sh_theme_light_onTertiaryContainer,
    error = sh_theme_light_error,
    errorContainer = sh_theme_light_errorContainer,
    onError = sh_theme_light_onError,
    onErrorContainer = sh_theme_light_onErrorContainer,
    surfaceContainerLowest = sh_theme_light_surfaceContainerLowest,
    surfaceContainerLow = sh_theme_light_surfaceContainerLow,
    surfaceContainerHigh = sh_theme_light_surfaceContainerHigh,
    surfaceBright = sh_theme_light_surfaceBright,
    background = sh_theme_light_background,
    onBackground = sh_theme_light_onBackground,
    surface = sh_theme_light_surface,
    surfaceContainer = sh_theme_light_surfaceContainer,
    surfaceContainerHighest = sh_theme_light_surfaceContainerHighest,
    onSurface = sh_theme_light_onSurface,
    surfaceVariant = sh_theme_light_surfaceVariant,
    onSurfaceVariant = sh_theme_light_onSurfaceVariant,
    outline = sh_theme_light_outline,
    inverseOnSurface = sh_theme_light_inverseOnSurface,
    inverseSurface = sh_theme_light_inverseSurface,
    inversePrimary = sh_theme_light_inversePrimary,
    outlineVariant = sh_theme_light_outlineVariant,
    scrim = sh_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = sh_theme_dark_primary,
    onPrimary = sh_theme_dark_onPrimary,
    primaryContainer = sh_theme_dark_primaryContainer,
    onPrimaryContainer = sh_theme_dark_onPrimaryContainer,
    secondary = sh_theme_dark_secondary,
    onSecondary = sh_theme_dark_onSecondary,
    secondaryContainer = sh_theme_dark_secondaryContainer,
    onSecondaryContainer = sh_theme_dark_onSecondaryContainer,
    tertiary = sh_theme_dark_tertiary,
    onTertiary = sh_theme_dark_onTertiary,
    tertiaryContainer = sh_theme_dark_tertiaryContainer,
    onTertiaryContainer = sh_theme_dark_onTertiaryContainer,
    error = sh_theme_dark_error,
    errorContainer = sh_theme_dark_errorContainer,
    onError = sh_theme_dark_onError,
    onErrorContainer = sh_theme_dark_onErrorContainer,
    surfaceContainerLowest = sh_theme_dark_surfaceContainerLowest,
    surfaceContainerLow = sh_theme_dark_surfaceContainerLow,
    surfaceContainerHigh = sh_theme_dark_surfaceContainerHigh,
    surfaceBright = sh_theme_dark_surfaceBright,
    background = sh_theme_dark_background,
    onBackground = sh_theme_dark_onBackground,
    surface = sh_theme_dark_surface,
    onSurface = sh_theme_dark_onSurface,
    surfaceContainer = sh_theme_dark_surfaceContainer,
    surfaceContainerHighest = sh_theme_dark_surfaceContainerHighest,
    surfaceVariant = sh_theme_dark_surfaceVariant,
    onSurfaceVariant = sh_theme_dark_onSurfaceVariant,
    outline = sh_theme_dark_outline,
    inverseOnSurface = sh_theme_dark_inverseOnSurface,
    inverseSurface = sh_theme_dark_inverseSurface,
    inversePrimary = sh_theme_dark_inversePrimary,
    outlineVariant = sh_theme_dark_outlineVariant,
    scrim = sh_theme_dark_scrim,
)

@Composable
fun ShTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = ShShapes,
        typography = ShTypography,
        content = content
    )
}
