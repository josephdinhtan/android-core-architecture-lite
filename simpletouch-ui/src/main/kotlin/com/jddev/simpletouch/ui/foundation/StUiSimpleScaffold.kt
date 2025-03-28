package com.jddev.simpletouch.ui.foundation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StUiSimpleScaffold(
    modifier: Modifier = Modifier,
    title: String,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    onBack: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            topBar ?: StUiTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
                title = title,
                onBack = { onBack() },
            )
        },
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton
    ) {
        Column (Modifier.padding(it).fillMaxSize()) {
            content()
        }
    }
}