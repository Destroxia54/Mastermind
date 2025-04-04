package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mastermindgame.logic.GameState

@Composable
fun GameEntryScreen(
    onSinglePlayer: () -> Unit,
    onLocalMultiplayer: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🎯 Mastermind", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onSinglePlayer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Single Player")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLocalMultiplayer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Local Multiplayer")
        }
    }
}

