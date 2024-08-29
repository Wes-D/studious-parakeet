package com.example.lifespark.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import com.example.lifespark.CharacterViewModel
import com.example.lifespark.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NPCSummaryScreen(
    viewModel: CharacterViewModel,
    onEdit: () -> Unit,
    onSave: () -> Unit,
    onExportPDF: () -> Unit,
    onReroll: () -> Unit,
    onReturnToMain: () -> Unit,
    isNameLocked: Boolean,
    isPortraitLocked: Boolean
) {
    val npcName = viewModel.npcName.value
    val npcRace = viewModel.selectedRace.value
    val npcArchetype = viewModel.selectedArchetype.value
    val npcTraits = viewModel.selectedTraits.value
    val npcBackstory = viewModel.npcBackstory.value
    val npcQuirk = viewModel.npcQuirk.value
    val npcPortrait = viewModel.npcPortrait.value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("NPC Overview") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // NPC Portrait
                if (npcPortrait != null) {
                    Image(
                        painter = npcPortrait,
                        contentDescription = "NPC Portrait",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color.Gray, CircleShape)
                    )
                } else {
                    Text("Generating Portrait...")
                }

                // NPC Name
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = npcName, style = MaterialTheme.typography.headlineMedium)
                    IconButton(onClick = { viewModel.rerollNPC(isNameLocked, isPortraitLocked) }) {
                        Icon(
                            painter = if (isNameLocked) painterResource(id = R.drawable.lock_closed)
                            else painterResource(id = R.drawable.lock_open),
                            contentDescription = "Lock Name"
                        )
                    }
                }

                // NPC Race and Archetype
                Text(text = "$npcRace - $npcArchetype", style = MaterialTheme.typography.bodyLarge)

                // NPC Traits and Quirk
                Text(text = "Traits: ${npcTraits.joinToString(", ")}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Quirk: $npcQuirk", style = MaterialTheme.typography.bodyLarge)

                // NPC Backstory
                Text(text = "Backstory:", style = MaterialTheme.typography.headlineSmall)
                Text(text = npcBackstory, style = MaterialTheme.typography.bodyMedium)

                // Action Buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onEdit) { Text("Edit") }
                    Button(onClick = onSave) { Text("Save") }
                    Button(onClick = onExportPDF) { Text("Export PDF") }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onReroll) { Text("Reroll") }
                    Button(onClick = onReturnToMain) { Text("Return to Main") }
                }
            }
        }
    }
}




