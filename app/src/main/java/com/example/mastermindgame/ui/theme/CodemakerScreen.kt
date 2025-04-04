package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mastermindgame.model.ColorPeg
import com.example.mastermindgame.logic.GameState

@Composable
fun CodemakerScreen(
    onCodeConfirmed: (List<ColorPeg>, String) -> Unit
) {
    var selectedCode by remember { mutableStateOf(listOf<ColorPeg>()) }
    var pin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🔐 Set Your Secret Code", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Tap colors to add to the code (4 total):")

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColorPeg.values().forEach { peg ->
                ColorButton(
                    color = peg.color,
                    onClick = {
                        if (selectedCode.size < 4) {
                            selectedCode = selectedCode + peg
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Selected Code:")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            selectedCode.forEach { peg ->
                ColorButton(color = peg.color, onClick = {}, showBorder = false)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { selectedCode = listOf() },
            enabled = selectedCode.isNotEmpty()
        ) {
            Text("Reset Code")
        }

        Spacer(modifier = Modifier.height(16.dp))
        PinKeypad(
            pinValue = pin,
            onPinChange = { pin = it }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (selectedCode.size == 4 && pin.isNotBlank()) {
                    GameState.secretCode = selectedCode
                    GameState.pin = pin
                    GameState.guessesLeft = 12
                    onCodeConfirmed(selectedCode, pin)
                }
            },
            enabled = selectedCode.size == 4 && pin.isNotBlank()
        ) {
            Text("Lock Code & Start Game")
        }
    }
}
