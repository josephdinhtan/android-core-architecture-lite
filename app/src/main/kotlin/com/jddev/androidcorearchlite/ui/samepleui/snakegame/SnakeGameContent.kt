package com.jddev.androidcorearchlite.ui.samepleui.snakegame

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.StUiPreview
import com.jddev.simpletouch.ui.StUiPreviewWrapper
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SnakeGameContent() {
    // Game states
    var snake by remember { mutableStateOf(listOf(Pair(5, 5))) }
    var food by remember { mutableStateOf(generateFood(snake)) }
    var direction by remember { mutableStateOf(Direction.RIGHT) }
    var gameOver by remember { mutableStateOf(false) }
    val gridSize = 20
    var speed by remember { mutableStateOf(200L) }

    // Game loop
    LaunchedEffect(Unit) {
        while (!gameOver) {
            delay(speed) // Tốc độ game
            snake = moveSnake(snake, direction, gridSize)

            // Check ăn thức ăn
            if (snake.first() == food) {
                snake = growSnake(snake)
                food = generateFood(snake)
            } else {
                // Check va chạm
                gameOver = checkCollision(snake, gridSize)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game board
        Box(
            modifier = Modifier
                .size((gridSize * 20).dp)
                .background(Color.Gray)
        ) {
            // Draw snake
            snake.forEach { pos ->
                val offset by animateIntOffsetAsState(
                    targetValue = IntOffset((pos.first * 20), (pos.second * 20)),
                    label = "offset",
                    animationSpec = tween(durationMillis = speed.toInt())
                )
                Box(
                    modifier = Modifier
                        .offset((pos.first * 20).dp, (pos.second * 20).dp)
                        //.offset {offset}
                        .size(20.dp)
                        .background(Color.Green)
                )
            }

            // Draw food
            Box(
                modifier = Modifier
                    .offset { IntOffset((food.first * 20).dp.roundToPx(), (food.second * 20).dp.roundToPx()) }
                    .size(20.dp)
                    .background(Color.Red)
            )
        }

        // Controls
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { speed = 100L }) { Text("100") }
            Button(onClick = { speed = 300L }) { Text("300") }
            Button(onClick = { speed = 500L }) { Text("500") }
            Button(onClick = { speed = 700L }) { Text("700") }
            Button(onClick = { speed = 1000L }) { Text("1s") }
        }
        Spacer(Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { direction = Direction.UP }) { Text("Up") }
            Row {
                Button(onClick = { direction = Direction.LEFT }) { Text("Left") }
                Spacer(Modifier.width(50.dp))
                Button(onClick = { direction = Direction.RIGHT }) { Text("Right") }
            }
            Button(onClick = { direction = Direction.DOWN }) { Text("Down") }
        }
        Spacer(Modifier.height(48.dp))
        if (gameOver) {
            Text("Game Over!", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

private enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

private fun moveSnake(snake: List<Pair<Int, Int>>, direction: Direction, gridSize: Int): List<Pair<Int, Int>> {
    val head = snake.first()
    val newHead = when (direction) {
        Direction.UP -> Pair(head.first, (head.second - 1 + gridSize) % gridSize)
        Direction.DOWN -> Pair(head.first, (head.second + 1) % gridSize)
        Direction.LEFT -> Pair((head.first - 1 + gridSize) % gridSize, head.second)
        Direction.RIGHT -> Pair((head.first + 1) % gridSize, head.second)
    }
    return listOf(newHead) + snake.dropLast(1)
}

private fun growSnake(snake: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    return snake + snake.last()
}

private fun generateFood(snake: List<Pair<Int, Int>>): Pair<Int, Int> {
    var food: Pair<Int, Int>
    do {
        food = Pair(Random.nextInt(20), Random.nextInt(20))
    } while (snake.contains(food))
    return food
}

private fun checkCollision(snake: List<Pair<Int, Int>>, gridSize: Int): Boolean {
    val head = snake.first()
    return snake.drop(1).contains(head) // Check va chạm với thân
}

@Composable
@StUiPreview
fun SnakeGamePreview() {
    StUiPreviewWrapper {
        SnakeGameContent()
    }
}