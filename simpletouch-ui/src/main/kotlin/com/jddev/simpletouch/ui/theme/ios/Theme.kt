package com.jddev.simpletouch.ui.theme.ios

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.jddev.simpletouch.ui.theme.standard.StUiShapes
import com.jddev.simpletouch.ui.theme.standard.StUiTypography
import com.jddev.simpletouch.ui.theme.standard.StandardDarkColors
import com.jddev.simpletouch.ui.theme.standard.StandardLightColors

internal val CupertinoLightColors = CupertinoColorScheme(
    blue = ios_theme_light_blue,
    brown = ios_theme_light_brown,
    cyan = ios_theme_light_cyan,
    green = ios_theme_light_green,
    indigo = ios_theme_light_indigo,
    mint = ios_theme_light_mint,
    orange = ios_theme_light_orange,
    pink = ios_theme_light_pink,
    purple = ios_theme_light_purple,
    red = ios_theme_light_red,
    teal = ios_theme_light_teal,
    yellow = ios_theme_light_yellow,
    gray = ios_theme_light_gray,
    gray2 = ios_theme_light_gray_2,
    gray3 = ios_theme_light_gray_3,
    gray4 = ios_theme_light_gray_4,
    gray5 = ios_theme_light_gray_5,
    gray6 = ios_theme_light_gray_6,
    primaryLabel = ios_theme_light_primary_label,
    secondaryLabel = ios_theme_light_secondary_label,
    tertiaryLabel = ios_theme_light_tertiary_label,
    quaternaryLabel = ios_theme_light_quaternary_label,
    placeholderText = ios_theme_light_placeholder_text,
    link = ios_theme_light_link,
    primaryBackground = ios_theme_light_primary_background,
    secondaryBackground = ios_theme_light_secondary_background,
    tertiaryBackground = ios_theme_light_tertiary_background,
    groupedBackground = ios_theme_light_grouped_background,
    secondaryGroupedBackground = ios_theme_light_secondary_grouped_background,
    tertiaryGroupedBackground = ios_theme_light_tertiary_grouped_background,
    fill = ios_theme_light_fill,
    secondaryFill = ios_theme_light_secondary_fill,
    tertiaryFill = ios_theme_light_tertiary_fill,
    quaternaryFill = ios_theme_light_quaternary_fill,
    separator = ios_theme_light_separator,
    opaqueSeparator = ios_theme_light_opaque_separator,
    nonadaptable = ios_theme_light_nonadaptable,
)

internal val CupertinoDarkColors = CupertinoColorScheme(
    blue = ios_theme_dark_blue,
    brown = ios_theme_dark_brown,
    cyan = ios_theme_dark_cyan,
    green = ios_theme_dark_green,
    indigo = ios_theme_dark_indigo,
    mint = ios_theme_dark_mint,
    orange = ios_theme_dark_orange,
    pink = ios_theme_dark_pink,
    purple = ios_theme_dark_purple,
    red = ios_theme_dark_red,
    teal = ios_theme_dark_teal,
    yellow = ios_theme_dark_yellow,
    gray = ios_theme_dark_gray,
    gray2 = ios_theme_dark_gray_2,
    gray3 = ios_theme_dark_gray_3,
    gray4 = ios_theme_dark_gray_4,
    gray5 = ios_theme_dark_gray_5,
    gray6 = ios_theme_dark_gray_6,
    primaryLabel = ios_theme_dark_primary_label,
    secondaryLabel = ios_theme_dark_secondary_label,
    tertiaryLabel = ios_theme_dark_tertiary_label,
    quaternaryLabel = ios_theme_dark_quaternary_label,
    placeholderText = ios_theme_dark_placeholder_text,
    link = ios_theme_dark_link,
    primaryBackground = ios_theme_dark_primary_background,
    secondaryBackground = ios_theme_dark_secondary_background,
    tertiaryBackground = ios_theme_dark_tertiary_background,
    groupedBackground = ios_theme_dark_grouped_background,
    secondaryGroupedBackground = ios_theme_dark_secondary_grouped_background,
    tertiaryGroupedBackground = ios_theme_dark_tertiary_grouped_background,
    fill = ios_theme_dark_fill,
    secondaryFill = ios_theme_dark_secondary_fill,
    tertiaryFill = ios_theme_dark_tertiary_fill,
    quaternaryFill = ios_theme_dark_quaternary_fill,
    separator = ios_theme_dark_separator,
    opaqueSeparator = ios_theme_dark_opaque_separator,
    nonadaptable = ios_theme_dark_nonadaptable,
)
private val LocalCupertinoColors: ProvidableCompositionLocal<CupertinoColorScheme> =
    compositionLocalOf { CupertinoLightColors }

object CupertinoTheme {
    val colorScheme: CupertinoColorScheme
        @Composable @ReadOnlyComposable get() = LocalCupertinoColors.current

//    val typography: Typography
//        @Composable @ReadOnlyComposable get() = LocalCupertinoTypography.current
//
//    val shapes: Shapes
//        @Composable @ReadOnlyComposable get() = LocalCupertinoShapes.current
}

@Composable
fun StUiCupertinoTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val cupertinoColorScheme = if (isDarkTheme) CupertinoDarkColors else CupertinoLightColors
    val materialColorScheme = if (isDarkTheme) StandardDarkColors else StandardLightColors
    CompositionLocalProvider(LocalCupertinoColors provides cupertinoColorScheme) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            shapes = StUiShapes,
            typography = StUiTypography,
            content = content
        )
    }
}