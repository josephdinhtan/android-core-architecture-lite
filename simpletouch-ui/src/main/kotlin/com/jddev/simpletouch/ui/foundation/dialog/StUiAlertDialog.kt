package com.jddev.simpletouch.ui.foundation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiHorizontalDivider
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper

@Composable
fun StUiAlertDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    icon: @Composable (() -> Unit)? = null,
    confirmButtonText: String?,
    dismissButtonText: String? = null,
    title: String?,
    text: String?,
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit),
) {
    StUiBaseDialog(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
    ) {
        StUiAlertDialogContent(
            modifier = modifier,
            icon = icon,
            confirmButtonText = confirmButtonText,
            dismissButtonText = dismissButtonText,
            title = title,
            text = text,
            onConfirm = onConfirm,
            onDismiss = onDismiss,
        )
    }
}

@Composable
private fun StUiAlertDialogContent(
    modifier: Modifier,
    icon: @Composable (() -> Unit)?,
    confirmButtonText: String?,
    dismissButtonText: String? = null,
    title: String?,
    text: String?,
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
) {
    Box(modifier
        .pointerInput(Unit) { detectTapGestures { } }
        .shadow(8.dp, shape = RoundedCornerShape(18.dp))
        .width(300.dp)
        .clip(RoundedCornerShape(18.dp))
        .background(
            MaterialTheme.colorScheme.surface,
        ), contentAlignment = Alignment.Center) {
        Column {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                icon?.let {
                    it()
                }
                title?.let {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.W700, textAlign = TextAlign.Center
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                text?.let {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
            confirmButtonText?.let {
                StUiDialogDivider()
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onConfirm?.invoke()
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp),
                        text = it,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700, textAlign = TextAlign.Center
                        ),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            dismissButtonText?.let {
                StUiDialogDivider()
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDismiss?.invoke()
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp),
                        text = it,
                        style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@Composable
private fun StUiDialogDivider() {
    StUiHorizontalDivider(
        color = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.7f)
    )
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(20.dp), contentAlignment = Alignment.Center
        ) {
            StUiAlertDialogContent(
                modifier = Modifier,
                icon = null,
                confirmButtonText = "Follow",
                dismissButtonText = "Cancel",
                title = "Follow me on Instagram Instagram",
                text = "@jddev_official",
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .padding(20.dp), contentAlignment = Alignment.Center
        ) {
            StUiAlertDialogContent(
                modifier = Modifier,
                icon = { Icon(Icons.Default.Info, null) },
                confirmButtonText = "Follow",
                dismissButtonText = "Cancel",
                title = "Follow me on Instagram",
                text = "@jddev_official",
            )
        }
    }
}