package com.jddev.simpletouch.ui.customization.camera

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomCameraController(
    modifier: Modifier = Modifier,
    captureFileUri: Uri?,
    isCameraImageUseCase: Boolean,
    capturing: Boolean,
    videoPause: Boolean,
    videoTimer: String,
    onCameraUiAction: (CameraUiAction) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedContent(
            modifier = Modifier.fillMaxWidth(),
            targetState = capturing && !isCameraImageUseCase,
            contentAlignment = Alignment.Center,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            },
            label = ""
        ) { capturing ->
            if (!capturing) {
                CameraUseCaseSelector(
                    isCameraImageUseCase = isCameraImageUseCase,
                    onSwitchCameraUseCase = {
                        onCameraUiAction(CameraUiAction.SwitchCameraUseCase(it))
                    }
                )
            } else {
                CameraVideoCapturingStopwatch(
                    videoPause = videoPause, videoTimer = videoTimer
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCameraButton(
                modifier = Modifier.padding(8.dp),
                enabled = !capturing,
                uri = if (!capturing) captureFileUri else null,
                imageClicked = { onCameraUiAction(CameraUiAction.ImageView(it)) },
            )
            if (isCameraImageUseCase) {
                CameraCapturePhotoButton(capturing = capturing,
                    onCapture = { onCameraUiAction(CameraUiAction.ImageCapture) })
            } else {
                CameraCaptureVideoButton(capturing = capturing,
                    onCapture = { onCameraUiAction(CameraUiAction.VideoCapture) },
                    onStopCapturing = { onCameraUiAction(CameraUiAction.StopVideoCapture) })
            }
            CameraSwitchButton(enabled = !capturing, onSwitch = {
                onCameraUiAction(CameraUiAction.SwitchCameraLens)
            })
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        var isCameraImageUseCase by remember { mutableStateOf(true) }
        var currentVideoPause by remember { mutableStateOf(false) }
        var currentCapturing by remember { mutableStateOf(true) }
        var currentVideoTimer by remember { mutableStateOf("00:00") }

        BottomCameraController(modifier = Modifier.fillMaxWidth(),
            isCameraImageUseCase = isCameraImageUseCase,
            capturing = currentCapturing,
            videoPause = currentVideoPause,
            videoTimer = currentVideoTimer,
            captureFileUri = null,
            onCameraUiAction = { action ->
                when (action) {
                    CameraUiAction.ImageCapture -> {
                        currentCapturing = true
                    }

                    CameraUiAction.VideoCapture -> {
                        currentCapturing = true
                    }

                    CameraUiAction.StopVideoCapture -> {
                        currentCapturing = false
                    }

                    is CameraUiAction.SwitchCameraUseCase -> {
                        isCameraImageUseCase = action.isCameraImageUseCase
                    }

                    is CameraUiAction.SwitchCameraLens -> {}
                    is CameraUiAction.ImageView -> {}
                }
            })
    }
}