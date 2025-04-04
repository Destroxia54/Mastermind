package com.example.mastermindgame.ui.theme

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mastermindgame.logic.GameState
import com.example.mastermindgame.model.ColorPeg

@Composable
fun RestartScreen(navController: NavController) {
    // 👇 Visual placeholder to avoid white screen flash
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)), // dark background
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Preparing next game...",
            color = Color.White,
            fontSize = 18.sp
        )
    }

    // ✅ Navigate after short delay
    Handler(Looper.getMainLooper()).postDelayed({
        if (GameState.singlePlayerMode) {
            GameState.reset()
            GameState.secretCode = List(4) { ColorPeg.values().random() }
            GameState.autoHintEnabled = true
            GameState.singlePlayerMode = true

            navController.navigate("codebreaker") {
                popUpTo("entry") { inclusive = true }
            }
        } else {
            GameState.reset()
            GameState.autoHintEnabled = false
            GameState.singlePlayerMode = false

            navController.navigate("codemaker") {
                popUpTo("entry") { inclusive = true }
            }
        }
    }, 300)
}

