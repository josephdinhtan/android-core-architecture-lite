package com.jddev.simpletouch.ui.customization.camera

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cameraswitch
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun CameraSwitchButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onSwitch: () -> Unit
) {
    FilledIconButton(
        modifier = modifier.clip(CircleShape).size(50.dp),
        enabled = enabled,
        onClick = onSwitch,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.background.copy(
                0.5f
            ), contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Icon(
            imageVector = Icons.Rounded.Cameraswitch, contentDescription = "Switch camera"
        )
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        CameraSwitchButton(onSwitch = {})
    }
}