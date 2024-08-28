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
    viewModel: CharacterViewModel, // ViewModel provides NPC data
    onEdit: () -> Unit,
    onSave: () -> Unit,
    onExportPDF: () -> Unit,
    onReroll: () -> Unit,
    onReturnToMain: () -> Unit,
    isNameLocked: Boolean, // Lock state for reroll
    isPortraitLocked: Boolean
) {
    val npcName = viewModel.npcName.value
    val npcRace = viewModel.selectedRace.value
    val npcArchetype = viewModel.selectedArchetype.value
    val npcTraits = viewModel.selectedTraits.value
    val npcBackstory = viewModel.npcBackstory.value
    val npcQuirk = viewModel.npcQuirk.value
    var npcPortrait = viewModel.npcPortrait.value
    val backgroundPainter: Painter = painterResource(id = R.drawable.background_parchment)

    npcPortrait = painterResource(id = R.drawable.alignment_true_neutral)

    Scaffold(
        topBar = {
            // Custom TopAppBar with decoration and soft shadow
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFFBE9E7)) // Parchment-like color
                    .shadow(2.dp) // Soft shadow for depth
            ) {
                // The main title text centered
                Text(
                    text = "Basic Information",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF3E2723)
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            // Image as a background
            Image(
                painter = backgroundPainter,
                contentDescription = null,
                contentScale = ContentScale.Crop, // Scale the image to fill the entire box
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //move this later when it makes sense.
                @Composable
                fun DisplayNPCPortrait(viewModel: CharacterViewModel) {
                    val portraitResourceId = viewModel.generateRandomPortrait(viewModel.selectedRace.value)

                    Image(
                        painter = painterResource(id = portraitResourceId),
                        contentDescription = "NPC Portrait",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color.Gray, CircleShape)
                    )
                }
                // Display the portrait by calling the function
                DisplayNPCPortrait(viewModel = CharacterViewModel())


                // NPC Name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = npcName, style = MaterialTheme.typography.headlineMedium)
                    IconButton(onClick = { viewModel.rerollNPC(isNameLocked, isPortraitLocked) }) {
                        Icon(
                            painter =
                                if (isNameLocked) painterResource(id = R.drawable.lock_closed)
                                else painterResource(id = R.drawable.lock_open),
                            contentDescription = "Lock Name"
                        )
                    }
                }

                // NPC Race and Archetype
                Text(
                    text = "$npcRace - $npcArchetype",
                    style = MaterialTheme.typography.bodyLarge
                )

                // NPC Traits
                Text(
                    text = "Traits: ${npcTraits.joinToString(", ")}",
                    style = MaterialTheme.typography.bodyLarge
                )

                // NPC Quirk
                Text(
                    text = "Quirk: $npcQuirk",
                    style = MaterialTheme.typography.bodyLarge
                )

                // NPC Backstory
                Text(
                    text = "Backstory:",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = npcBackstory,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Action Buttons
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onEdit) {
                        Text("Edit")
                    }
                    Button(onClick = onSave) {
                        Text("Save")
                    }
                    Button(onClick = onExportPDF) {
                        Text("PDF")
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onReroll) {
                        Text("Reroll")
                    }
                    Button(onClick = onReturnToMain) {
                        Text("Main")
                    }
                }
            }
        }
    }
}



