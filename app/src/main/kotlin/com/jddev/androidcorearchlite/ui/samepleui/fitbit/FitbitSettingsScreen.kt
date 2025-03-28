package com.jddev.androidcorearchlite.ui.samepleui.fitbit

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Fitbit
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.jddev.simpletouch.ui.customization.settingsui.StSettingsUi
import com.jddev.simpletouch.ui.customization.settingsui.group.StSettingsGroup
import com.jddev.simpletouch.ui.customization.settingsui.navigation.StSettingsNavigateItem
import com.jddev.simpletouch.ui.foundation.topappbar.StUiLargeTopAppBar
import com.jddev.simpletouch.ui.foundation.topappbar.stUiLargeTopAppbarScrollBehavior
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

private val headerStyle
    @Composable get() = MaterialTheme.typography.titleMedium.copy(
        color = Color(0xFF5E7889),
        fontWeight = FontWeight.W600
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitbitSettingsScreen(
    onBack: () -> Unit,
) {
    val scrollBehavior = stUiLargeTopAppbarScrollBehavior()
    Scaffold(
        topBar = {
            StUiLargeTopAppBar(
                scrollBehavior = scrollBehavior, title = "Settings", onBack = onBack
            )
        },
    ) {
        StSettingsUi(
            modifier = Modifier.padding(it),
            scrollBehavior = scrollBehavior,
        ) {
            StSettingsGroup(
                header = "Your account",
                headerTextStyle = headerStyle,
            ) {
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Fitbit,
                    title = "Fitbit Premium",
                    subTitle = "Not enrolled",
                    onClick = {})
            }
            StSettingsGroup(
                header = "App settings",
                headerTextStyle = headerStyle
            ) {
                StSettingsNavigateItem(leadingImageVector = Icons.Default.Apps,
                    title = "Date, time & units",
                    onClick = {})
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Notifications,
                    title = "Push notifications",
                    onClick = {})
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Email,
                    title = "Email notifications",
                    onClick = {})
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Groups,
                    title = "Social & sharing",
                    onClick = {})
            }
            StSettingsGroup(
                header = "Preferences",
                headerTextStyle = headerStyle,
            ) {
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Accessibility,
                    title = "Activity",
                    onClick = {})
                StSettingsNavigateItem(
                    leadingImageVector = Icons.Outlined.DarkMode,
                    title = "Sleep",
                    onClick = {})
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Person,
                    title = "Stress & mindfulness",
                    onClick = {})
                StSettingsNavigateItem(leadingImageVector = Icons.Outlined.Book,
                    title = "Nutrition & weight",
                    onClick = {})
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        FitbitSettingsScreen(onBack = {})
    }
}