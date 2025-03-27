package com.jddev.androidcorearchlite.ui.archcatalog.statemachinedemo

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.foundation.StUiSimpleScaffold
import com.jddev.simpletouch.ui.theme.StUiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateMachineScreen(
    modifier: Modifier = Modifier,
    oldState: String,
    currentState: String,
    lastEvent: String,
    makeHeat: () -> Unit,
    makeCool: () -> Unit,
    onBack: () -> Unit,
) {
    StUiSimpleScaffold(
        modifier = modifier,
        title = "State Machine",
        onBack = onBack,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Water State", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(32.dp))
            AnimatedContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                targetState = currentState,
                transitionSpec = {
                    slideInVertically { height -> -height } + fadeIn() togetherWith
                            slideOutVertically { height -> height } + fadeOut() using (
                            SizeTransform(clip = false)
                            )
                }, label = "animated content"
            ) { targetState ->
                Text(
                    text = targetState,
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Old State: $oldState")
            Text(text = "Last Event: $lastEvent")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = makeHeat,
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Trigger Heat")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = makeCool,
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Trigger Cool")
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    StUiTheme {
        StateMachineScreen(
            currentState = "NewState",
            oldState = "Ice",
            lastEvent = "Event",
            makeHeat = {},
            makeCool = {},
            onBack = {},
        )
    }
}