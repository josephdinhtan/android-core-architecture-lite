package com.jddev.simpletouch.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StUiScrollBehavior() =
    TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
