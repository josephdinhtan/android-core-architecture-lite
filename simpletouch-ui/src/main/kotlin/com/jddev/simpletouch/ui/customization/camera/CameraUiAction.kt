package com.jddev.simpletouch.ui.customization.camera

import android.net.Uri

sealed interface CameraUiAction {
    data object ImageCapture : CameraUiAction
    data object VideoCapture : CameraUiAction
    data object StopVideoCapture : CameraUiAction
    data class SwitchCameraUseCase(val isCameraImageUseCase: Boolean) : CameraUiAction
    data object SwitchCameraLens : CameraUiAction
    data class ImageView(val uri: Uri?) : CameraUiAction
}