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
    onCodemakerClick: () -> Unit,
    onCodebreakerClick: () -> Unit
) {
    var autoHints by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🎯 Mastermind", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Auto Hints")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = autoHints,
                onCheckedChange = { autoHints = it }
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                GameState.autoHintEnabled = autoHints
                onCodemakerClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Be the Codemaker")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                GameState.autoHintEnabled = autoHints
                onCodebreakerClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Be the Codebreaker")
        }
    }
}
