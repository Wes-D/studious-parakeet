package com.example.lifespark.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.lifespark.NPC
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterOverviewScreen(
    npc: NPC,  // NPC is passed directly here
    onEditCharacter: () -> Unit,
    onSaveCharacter: () -> Unit,
    onExportCharacter: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("NPC Summary") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Display NPC details
            Text(text = "Name: ${npc.name}", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Race: ${npc.race}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Archetype: ${npc.archetype}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Gender: ${npc.gender}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Alignment: ${npc.alignment}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Traits: ${npc.traits.joinToString()}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Backstory: ${npc.backstory}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons for saving, editing, and exporting
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onEditCharacter) {
                    Text("Edit Character")
                }
                Button(onClick = onSaveCharacter) {
                    Text("Save Character")
                }
                Button(onClick = onExportCharacter) {
                    Text("Export as PDF")
                }
            }
        }
    }
}

