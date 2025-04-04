package com.example.mastermindgame.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mastermindgame.logic.GameState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import com.example.mastermindgame.model.ColorPeg

@Composable
fun GameOverScreen(
    navController: NavController,
    won: Boolean,
    guessesUsed: Int,
    correctCode: List<ColorPeg>,
    isSinglePlayer: Boolean,
    onReturnToMenu: () -> Unit
){
    val message = if (won) "🎉 You Win!" else "💥 You Lose!"
    Log.d("GameOverDebug", "correctCode = ${correctCode.map { it.name }}")
    Log.d("GameOverDebug", "GameState.secretCode = ${GameState.secretCode.map { it.name }}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))

        Text("The correct code was:")
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { it / 2 })
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                correctCode.forEach { peg ->
                    ColorButton(color = peg.color, onClick = {}, showBorder = false)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        if (won) {
            Text("You cracked the code in $guessesUsed guesses!", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onReturnToMenu) {
                Text("Main Menu")
            }

            Button(onClick = {
                navController.navigate("restart")
            }) {
                Text("Play Again")
            }
        }
    }
}