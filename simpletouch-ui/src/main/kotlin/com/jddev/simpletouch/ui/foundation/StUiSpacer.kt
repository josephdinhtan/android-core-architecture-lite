package com.jddev.simpletouch.ui.foundation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StSpacerVerticalSmall() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun StSpacerVerticalMedium() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun StSpacerVerticalLarge() {
    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
fun StSpacerHorizontalSmall() {
    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun StSpacerHorizontalMedium() {
    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun StSpacerHorizontalLarge() {
    Spacer(modifier = Modifier.width(32.dp))
}