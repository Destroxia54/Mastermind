package com.example.mastermindgame.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mastermindgame.R
import com.example.mastermindgame.logic.GameState

@Composable
fun GameEntryScreen(
    onSinglePlayer: () -> Unit,
    onLocalMultiplayer: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 🎨 Background image
        Image(
            painter = painterResource(id = R.drawable.mastermind_menu),
            contentDescription = "Mastermind Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // 🧠 Button overlay (centered column)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // 👈 Important
        ) {
            Spacer(modifier = Modifier.height(500.dp)) // 👈 Push everything downward

            Image(
                painter = painterResource(id = R.drawable.mastermind_menu_single_player_btn),
                contentDescription = "Single Player",
                modifier = Modifier
                    .width(220.dp)
                    .clickable { onSinglePlayer() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.mastermind_menu_local_multiplayer_btn),
                contentDescription = "Local Multiplayer",
                modifier = Modifier
                    .width(220.dp)
                    .clickable { onLocalMultiplayer() }
            )
        }
    }
}




