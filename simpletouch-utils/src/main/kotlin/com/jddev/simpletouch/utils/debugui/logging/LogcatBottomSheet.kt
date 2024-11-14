package com.jddev.simpletouch.utils.debugui.logging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.utils.logging.LogManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogcatBottomSheet(
    logManager: LogManager, clearLog: (() -> Unit)? = null, onDismissRequest: () -> Unit
) {
    val logcatList = logManager.logcatListView.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    ModalBottomSheet(modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        dragHandle = {}) {
        Column {
            LogcatContent(
                logcatList = logcatList.value,
                clearLog = clearLog,
                onDismissRequest = onDismissRequest
            )
        }
    }
}
