package com.example.mastermindgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mastermindgame.logic.GameState
import com.example.mastermindgame.ui.theme.GameEntryScreen
import com.example.mastermindgame.ui.theme.MastermindGameTheme
import com.example.mastermindgame.ui.theme.CodemakerScreen
import com.example.mastermindgame.ui.theme.CodebreakerScreen
import com.example.mastermindgame.ui.theme.GameOverScreen
import com.example.mastermindgame.ui.theme.CodeRevealScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MastermindGameTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MastermindNavHost()
                }
            }
        }
    }
}

@Composable
fun MastermindNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "entry") {
        composable("entry") {
            GameEntryScreen(
                onCodemakerClick = { navController.navigate("codemaker") },
                onCodebreakerClick = { navController.navigate("codebreaker") }
            )
        }

        // Codemaker screen - selects code + pin
        composable("codemaker") {
            CodemakerScreen { selectedCode, pin ->
                // For now, just go back to entry
                // Later: save selectedCode + pin in shared state
                navController.navigate("entry") {
                    popUpTo("entry") { inclusive = true }
                }
            }
        }

        // Codebreaker screen - makes guesses
        composable("codebreaker") {
            CodebreakerScreen(
                onGameOver = { won, _ ->
                    val result = if (won) "win" else "lose"
                    navController.navigate("gameover/$result")
                },
                onRevealCode = {
                    navController.navigate("reveal")
                }
            )
        }

        composable("gameover/{result}") { backStackEntry ->
            val result = backStackEntry.arguments?.getString("result")
            val won = result == "win"
            val guessesUsed = GameState.codebreakerGuesses.size


            GameOverScreen(
                won = won,
                guessesUsed = guessesUsed, // ✅ pass it into the screen
                onReturnToMenu = {
                    GameState.reset()
                    navController.navigate("entry") {
                        popUpTo("entry") { inclusive = true }
                    }
                },
                onPlayAgain = {
                    GameState.reset()
                    navController.navigate("codemaker") {
                        popUpTo("entry") { inclusive = true }
                    }
                }
            )
        }

        composable("reveal") {
            CodeRevealScreen {
                navController.popBackStack()
            }
        }

    }
}
