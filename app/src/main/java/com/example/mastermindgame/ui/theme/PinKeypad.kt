package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PinKeypad(
    pinValue: String,
    onPinChange: (String) -> Unit,
    maxDigits: Int = 4,
    showDelete: Boolean = true
) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Text("PIN: $pinValue")

        Spacer(modifier = Modifier.height(8.dp))

        val keys = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("Clr", "0", "Del")
        )

        keys.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { key ->
                    Button(
                        onClick = {
                            when (key) {
                                "Del" -> if (pinValue.isNotEmpty()) onPinChange(pinValue.dropLast(1))
                                "Clear" -> onPinChange("")
                                else -> if (pinValue.length < maxDigits) onPinChange(pinValue + key)
                            }
                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .width(72.dp)
                    ) {
                        Text(key)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}