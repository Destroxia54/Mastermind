package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mastermindgame.model.ColorPeg
import com.example.mastermindgame.logic.GameState
import com.example.mastermindgame.ui.theme.ColorButton
import com.example.mastermindgame.ui.theme.GuessRow
import com.example.mastermindgame.model.GuessResult

fun calculateHint(code: List<ColorPeg>, guess: List<ColorPeg>): Pair<Int, Int> {
    var black = 0
    var white = 0

    val codeCopy = code.map { it as ColorPeg? }.toMutableList()
    val guessCopy = guess.map { it as ColorPeg? }.toMutableList()

    for (i in code.indices) {
        if (guess[i] == code[i]) {
            black++
            codeCopy[i] = null
            guessCopy[i] = null
        }
    }

    for (i in guessCopy.indices) {
        val g = guessCopy[i] ?: continue
        if (codeCopy.contains(g)) {
            white++
            codeCopy[codeCopy.indexOf(g)] = null
        }
    }

    return Pair(black, white)
}

@Composable
fun CodebreakerScreen(
    onGameOver: (Boolean, List<ColorPeg>) -> Unit,
    onRevealCode: () -> Unit
){
    var currentGuess by remember { mutableStateOf(listOf<ColorPeg>()) }
    var guesses by remember { mutableStateOf(GameState.codebreakerGuesses) }
    var blackPegsInput by remember { mutableStateOf("") }
    var whitePegsInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🎯 Codebreaker", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Tap pegs to make your guess:")

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColorPeg.values().forEach { peg ->
                ColorButton(
                    color = peg.color,
                    onClick = {
                        if (currentGuess.size < 4) {
                            currentGuess = currentGuess + peg
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Current Guess:")

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            currentGuess.forEach { peg ->
                ColorButton(color = peg.color, onClick = {}, showBorder = false)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { currentGuess = listOf() },
            enabled = currentGuess.isNotEmpty()
        ) {
            Text("Clear Guess")
        }

        if (currentGuess.size == 4) {
            if (GameState.autoHintEnabled) {
                val (black, white) = calculateHint(GameState.secretCode, currentGuess)
                guesses = guesses + GuessResult(currentGuess, black, white)
                GameState.codebreakerGuesses = guesses
                currentGuess = listOf()

                if (black == 4) {
                    onGameOver(true, GameState.secretCode)
                } else if (guesses.size >= 11) {
                    onGameOver(false, GameState.secretCode)
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Codemaker, enter hint for this guess:")

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = blackPegsInput,
                        onValueChange = { blackPegsInput = it.filter { c -> c.isDigit() } },
                        label = { Text("Black Pegs") },
                        modifier = Modifier.width(120.dp)
                    )
                    OutlinedTextField(
                        value = whitePegsInput,
                        onValueChange = { whitePegsInput = it.filter { c -> c.isDigit() } },
                        label = { Text("White Pegs") },
                        modifier = Modifier.width(120.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    val black = blackPegsInput.toIntOrNull() ?: 0
                    val white = whitePegsInput.toIntOrNull() ?: 0
                    guesses = guesses + GuessResult(currentGuess, black, white)
                    GameState.codebreakerGuesses = guesses
                    currentGuess = listOf()
                    blackPegsInput = ""
                    whitePegsInput = ""

                    if (black == 4) {
                        onGameOver(true, GameState.secretCode)
                    } else if (guesses.size >= 11) {
                        onGameOver(false, GameState.secretCode)
                    }

                }, enabled = blackPegsInput.isNotEmpty() && whitePegsInput.isNotEmpty()) {
                    Text("Submit Guess")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Previous Guesses:")

        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            guesses.asReversed().forEachIndexed { index, result ->
                val isMostRecent = index == 0
                GuessRow(
                    guess = result.guess,
                    black = result.blackPegs,
                    white = result.whitePegs,
                    highlight = isMostRecent
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            onRevealCode()
        }) {
            Text("Reveal Code (Codemaker Only)")
        }
    }
}

