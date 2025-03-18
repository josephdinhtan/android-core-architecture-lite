package com.jddev.simpletouch.ui.customization.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun CameraUseCaseSelector(
    modifier: Modifier = Modifier,
    isCameraImageUseCase: Boolean,
    onSwitchCameraUseCase: (isCameraImageUseCase: Boolean) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = if (isCameraImageUseCase) 0 else 1,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        divider = { Divider(color = Color.Transparent) },
    ) {
        Tab(
            selected = isCameraImageUseCase,
            onClick = {
                onSwitchCameraUseCase(true)
            }, text = {
                if (isCameraImageUseCase) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                        Alignment.Center
                    ) {
                        Text(
                            text = "Photo",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(
                                horizontal = 10.dp, vertical = 5.dp
                            )
                        )
                    }
                } else {
                    Text(
                        text = "Photo", maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                }
            }
        )
        Tab(
            selected = !isCameraImageUseCase,
            onClick = {
                onSwitchCameraUseCase(false)
            }, text = {
                if (!isCameraImageUseCase) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                        Alignment.Center
                    ) {
                        Text(
                            text = "Video",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(
                                horizontal = 10.dp, vertical = 5.dp
                            )
                        )
                    }
                } else {
                    Text(
                        text = "Video", maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                }
            }
        )
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        var isCameraImageUseCase by remember { mutableStateOf(true) }
        CameraUseCaseSelector(isCameraImageUseCase = isCameraImageUseCase) {
            isCameraImageUseCase = it
        }
    }
}