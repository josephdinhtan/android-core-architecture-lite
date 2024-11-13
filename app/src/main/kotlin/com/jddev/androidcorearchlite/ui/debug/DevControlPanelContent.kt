package com.jddev.androidcorearchlite.ui.debug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DevControlPanelContent(
    devUtility: DevUtility
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            8.dp, Alignment.CenterVertically
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton({
            devUtility.commandRequest()
        }) { Text("Command Request") }
        FilledTonalButton({
            devUtility.saveATestSettingsToDb()
        }) { Text("Save Test DbSettings") }
        FilledTonalButton({
            devUtility.getATestSettingsFromDb()
        }) { Text("Get DbSettings") }
        FilledTonalButton({
            devUtility.tryReportStatus()
        }) { Text("Send ReportStatus") }
        FilledTonalButton({
            devUtility.tryCaptureImageWithoutPreview()
        }) { Text("Capture Image in background") }
        FilledTonalButton({
            devUtility.tryUploadVideoToServer()
        }) { Text("Upload 0240925_154202855.mp4") }
    }
}