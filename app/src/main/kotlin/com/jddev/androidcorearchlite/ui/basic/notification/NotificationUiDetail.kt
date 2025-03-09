package com.jddev.androidcorearchlite.ui.basic.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import com.jddev.simpletouch.ui.foundation.StUiTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationUiDetail(
    onBack: () -> Unit,
    detailId: String,
) {
    Scaffold(
        topBar = {
            StUiTopAppBar(
                title = "Notification Detail",
                onBack = onBack,
            )
        }
    ) {
        Column(
            Modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Detail message", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(12.dp))
            Text(text = detailId, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        NotificationUiDetail(
            onBack = {},
            detailId = "From main route",
        )
    }
}