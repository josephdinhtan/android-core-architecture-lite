package com.jddev.simpletouch.utils.debugui.logging

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jddev.simpletouch.utils.logging.LogModel

@Composable
internal fun LogcatContent(
    modifier: Modifier = Modifier,
    logcatList: List<LogModel>,
    clearLog: (() -> Unit)? = null,
    onDismissRequest: () -> Unit
) {
    val rowScrollState = rememberScrollState()
    val columScrollState = rememberScrollState()
    var requestScrollToEnd by remember { mutableStateOf(true) }
    LaunchedEffect(requestScrollToEnd) {
        columScrollState.animateScrollTo(columScrollState.maxValue)
    }
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp, bottom = 2.dp),
        ) {
            Text(
                "Logcat",
                style = LocalTextStyle.current.copy(fontSize = 22.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp)
            )
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                clearLog?.let { clear ->
                    IconButton(onClick = clear) {
                        Icon(Icons.Outlined.Delete, null)
                    }
                }
                IconButton(onClick = {
                    requestScrollToEnd = !requestScrollToEnd
                }) {
                    Icon(Icons.Outlined.ArrowDownward, null)
                }
                IconButton(onClick = onDismissRequest) {
                    Icon(Icons.Outlined.Close, null)
                }
            }
        }
        Column(modifier = Modifier.verticalScroll(columScrollState)) {
            Row(modifier = Modifier.horizontalScroll(rowScrollState)) {
                Column(modifier = Modifier.wrapContentSize()) {
                    logcatList.forEach { logcat ->
                        Row(
                            modifier = Modifier.wrapContentWidth()
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(logcat.time, maxLines = 1)
                            Spacer(modifier = Modifier.width(8.dp))
                            LogIndicator(logcat.priority)
                            logcat.tag?.let {
                                Spacer(modifier = Modifier.width(8.dp))
                                LogMessage(logcat.priority, it)
                                //Text(it, maxLines = 1, color = LocalTextStyle.current.color)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(logcat.message, maxLines = 1, color = LocalTextStyle.current.color)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun LogcatBottomSheetPreview() {
    MaterialTheme {
        LogcatContent(logcatList = listOf(
            LogModel(
                "12:25:29.653", Log.VERBOSE, "TestTag", "Test Message"
            ),
            LogModel(
                "12:25:29.653",
                Log.DEBUG,
                "TestTag",
                "Test Message, Test Message, Test Message, Test Message, Test Message"
            ),
            LogModel(
                "12:25:29.653",
                Log.INFO,
                "TestTag",
                "Test Message, Test Message, Test Message, Test Message, Test Message, Test Message"
            ),
            LogModel(
                "12:25:29.653",
                Log.WARN,
                "TestTag",
                "Test Message, Test Message, Test Message, Test Message, Test Message, Test Message, Test Message, Test Message"
            ),
            LogModel(
                "12:25:29.653",
                Log.ERROR,
                "TestTag",
                "Test Message, Test Message, Test Message, Test Message, Test Message, Test Message, Test Message, Test Message"
            ),
            LogModel(
                "12:25:29.653",
                Log.ASSERT,
                "TestTag",
                "Test Message, Test Message, Test Message, Test Message, Test Message, Test Message, Test Message, Test Message"
            ),
        ), onDismissRequest = {}, clearLog = {})
    }
}