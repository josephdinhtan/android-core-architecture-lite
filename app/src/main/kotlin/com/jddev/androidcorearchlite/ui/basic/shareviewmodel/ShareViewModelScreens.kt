package com.jddev.androidcorearchlite.ui.basic.shareviewmodel

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareViewModelMainScreen(
    viewModel: ShareViewModelViewModel,
    level: Int = 0,
    onBack: () -> Unit,
    navigateToNextLevel: () -> Unit,
) {
    val countValue = viewModel.count.collectAsState()
    StUiSimpleScaffold(
        title = if (level == 0) "Share ViewModel" else "Share ViewModel level $level",
        onBack = onBack,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(32.dp))
            Text("Count value")
            Spacer(Modifier.height(32.dp))
            AnimatedContent(
                modifier = Modifier.padding(16.dp),
                targetState = countValue.value,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        slideInVertically { height -> height } + fadeIn() togetherWith
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() togetherWith
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }, label = "animated content"
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(16.dp))
        }
        ActionButton(
            onClick = { viewModel.increaseCount() },
            title = "Increment"
        )
        ActionButton(
            onClick = { viewModel.decreaseCount() },
            color = MaterialTheme.colorScheme.secondary,
            title = "Decrement"
        )
        Spacer(Modifier.height(16.dp))
        if (level < 2) {
            HorizontalDivider()
            Spacer(Modifier.height(16.dp))
            ActionButton(
                onClick = navigateToNextLevel,
                color = MaterialTheme.colorScheme.tertiary,
                title = "Navigate to level ${level + 1}"
            )
        }
    }
}



@Composable
private fun ActionButton(
    title: String,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = color)
    ) {
        Text(title)
    }
}