package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mastermindgame.logic.GameState

@Composable
fun CodeRevealScreen(
    onBackToGame: () -> Unit
) {
    var pinInput by remember { mutableStateOf("") }
    var showCode by remember { mutableStateOf(false) }
    var pinError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🔒 Enter PIN to Reveal Code", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        PinKeypad(
            pinValue = pinInput,
            onPinChange = {
                pinInput = it
                pinError = false
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (pinInput == GameState.pin) {
                showCode = true
            } else {
                pinError = true
                showCode = false
            }
        }) {
            Text("Reveal Code")
        }

        if (pinError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Incorrect PIN", color = MaterialTheme.colorScheme.error)
        }

        if (showCode) {
            Spacer(modifier = Modifier.height(24.dp))
            Text("Secret Code:")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GameState.secretCode.forEach { peg ->
                    ColorButton(color = peg.color, onClick = {}, showBorder = false)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBackToGame) {
            Text("Return to Game")
        }
    }
}
