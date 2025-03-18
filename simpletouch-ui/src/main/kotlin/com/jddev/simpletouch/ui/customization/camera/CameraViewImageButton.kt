package com.jddev.simpletouch.ui.customization.camera

import android.media.MediaScannerConnection
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Photo
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import com.google.accompanist.glide.rememberGlidePainter
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import timber.log.Timber

@Composable
fun ImageCameraButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uri: Uri? = null,
    imageClicked: (Uri) -> Unit
) {
    Box(modifier = modifier.size(50.dp)) {
        if (uri == null) {
            Icon(
                imageVector = Icons.Rounded.Photo, contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)

            )
        } else {
            Image(
                painter = rememberGlidePainter(uri, fadeIn = true),
                contentDescription = "Captured Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(percent = 10))
                    .clickable {
                        if (enabled) imageClicked(uri)
                    })

            val mimeType =
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(uri.toFile().extension)
            MediaScannerConnection.scanFile(
                LocalContext.current, arrayOf(uri.toFile().absolutePath), arrayOf(mimeType)
            ) { _, uri ->
                uri?.let { Timber.d("Image capture scanned into media store: $it") }
            }
        }
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        ImageCameraButton(
            uri = null,
            imageClicked = {}
        )
    }
}