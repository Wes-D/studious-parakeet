package com.example.lifespark.ui.screens

import androidx.compose.runtime.Composable
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.lifespark.CharacterViewModel
import com.example.lifespark.R


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TraitsAndBackstoryScreen(
    viewModel: CharacterViewModel,
    onGenerateNPC: () -> Unit
) {
    // Access ViewModel's state
    val selectedGender = viewModel.selectedGender.value
    val selectedTraits = viewModel.selectedTraits.value
    val backstoryPrompt = viewModel.backstoryPrompt.value
    val traitOptions = listOf("Brave", "Calm", "Cunning", "Impulsive", "Wise", "Kind", "Mysterious", "Charming")
    val maxSelectedTraits = 3

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Your NPC - Step 2: Personality & Traits") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Use padding from Scaffold
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Progress Indicator
            Text("Step 2 of 2", style = MaterialTheme.typography.bodyMedium)

            // Gender Selection Chips
            GenderSelectionChips(
                selectedGender = selectedGender,
                onGenderSelected = { selectedGender ->
                    viewModel.selectedGender.value = selectedGender
                }
            )

            // Traits Selection Section
            Text("Select up to 3 Traits", style = MaterialTheme.typography.headlineMedium)

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalArrangement = Arrangement.Top
            ) {
                traitOptions.forEach { trait ->
                    val isSelected = selectedTraits.contains(trait)

                    CustomChip(
                        text = trait,
                        isSelected = isSelected,
                        onClick = {
                            if (isSelected) {
                                // Remove the trait if it is currently selected
                                viewModel.selectedTraits.value -= trait
                            } else if (viewModel.selectedTraits.value.size < maxSelectedTraits) {
                                // Add the trait if it is not selected and there is space
                                viewModel.selectedTraits.value += trait
                            }
                        }
                    )
                }
            }

            // Backstory Prompt Section
            Text("Enter a Backstory Prompt (Optional)", style = MaterialTheme.typography.headlineMedium)

            TextField(
                value = backstoryPrompt,
                onValueChange = { viewModel.backstoryPrompt.value = it },
                label = { Text("Enter a keyword or short sentence") },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Generate NPC Button
            Button(onClick = onGenerateNPC) {
                Text("Generate NPC")
            }
        }
    }
}
