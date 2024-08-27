package com.example.lifespark.ui.screens

import androidx.compose.runtime.Composable
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.example.lifespark.CharacterViewModel
import com.example.lifespark.R


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TraitsAndBackstoryScreen(
    viewModel: CharacterViewModel,
    onGenerateNPC: () -> Unit
) {
    val backgroundPainter: Painter = painterResource(id = R.drawable.background_parchment)
    // Access ViewModel's state
    val selectedGender = viewModel.selectedGender.value
    val selectedTraits = viewModel.selectedTraits.value
    val backstoryPrompt = viewModel.backstoryPrompt.value
    val traitOptions = listOf("Brave", "Calm", "Cunning", "Impulsive", "Wise", "Kind", "Mysterious", "Charming")
    val maxSelectedTraits = 3

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
                // Progress Indicator
                //Text("Step 2 of 2", style = MaterialTheme.typography.bodyMedium)

                // Gender Selection Chips
                GenderSelectionChips(
                    selectedGender = selectedGender,
                    onGenderSelected = { selectedGender ->
                        viewModel.selectedGender.value = selectedGender
                    }
                )

                // Traits Selection Section
                Text("Select up to 3 Traits", style = MaterialTheme.typography.bodyMedium)

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Add spacing between chips
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between rows of chips
                ) {
                    traitOptions.forEach { trait ->
                        val isSelected = selectedTraits.contains(trait)

                        TraitChip(
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
                Text(
                    "Backstory Prompt (Optional):",
                    style = MaterialTheme.typography.headlineSmall
                )

                TextField(
                    value = backstoryPrompt,
                    onValueChange = { viewModel.backstoryPrompt.value = it },
                    label = { Text("Enter a keyword or short sentence") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = false,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Generate NPC Button
                Button(onClick = onGenerateNPC) {
                    Text("Generate NPC")
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenderSelectionChips(
    selectedGender: String?,
    onGenderSelected: (String) -> Unit
) {
    val genderOptions = listOf("Male", "Female", "Non-binary", "Custom")

    // State for custom gender input
    var customGender by remember { mutableStateOf(selectedGender.takeIf { it !in genderOptions } ?: "") }
    var isCustomSelected by remember { mutableStateOf(selectedGender == "Custom" || selectedGender !in genderOptions) }
    var showError by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column {
        Text("Select Gender", style = MaterialTheme.typography.bodyMedium)

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            genderOptions.forEach { gender ->
                val isSelected = selectedGender == gender
                GenderChip(
                    text = gender,
                    isSelected = isSelected,
                    onClick = {
                        onGenderSelected(gender)
                        isCustomSelected = (gender == "Custom")
                        if (!isCustomSelected) customGender = "" // Reset custom input if not selected
                        showError = false // Reset error when switching selection
                    }
                )
            }
        }

        // Show custom gender input field with smooth animation
        AnimatedVisibility(
            visible = isCustomSelected,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                OutlinedTextField(
                    value = customGender,
                    onValueChange = { newValue ->
                        // Allow only letters, hyphens, apostrophes, and spaces; limit length to 20
                        val filteredText = newValue.filter { it.isLetter() || it == '-' || it == '\'' || it.isWhitespace() }
                        if (filteredText.length <= 20) {
                            customGender = filteredText
                        }
                    },
                    label = { Text("Enter Custom Gender") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            if (customGender.isBlank()) {
                                showError = true
                            } else {
                                onGenderSelected(customGender)
                                showError = false // Hide error when a valid input is submitted
                            }
                        }
                    ),
                    isError = showError // Display error if necessary
                )

                // Error message if input is invalid
                if (showError) {
                    Text(
                        text = "Please enter a valid gender (1-20 characters, no numbers)",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.padding(top = 8.dp))

                Button(
                    onClick = {
                        if (customGender.isBlank()) {
                            showError = true
                        } else {
                            onGenderSelected(customGender)
                            showError = false // Hide error when a valid input is submitted
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}

@Composable
fun GenderChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF9575CD)) // Fantasy purple for selected
        else BorderStroke(1.dp, Color(0xFFD7CCC8)), // Parchment-like color for unselected
        shadowElevation = if (isSelected) 8.dp else 2.dp, // Stronger shadow for selected chips
        color = Color.Transparent // Transparent color to allow background painter texture to show through
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFDE68A), // Lighter parchment color at top
                            Color(0xFFD7CCC8)  // Darker parchment color at bottom
                        )
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) Color(0xFF4CAF50) // Green for selected text
                    else Color(0xFF3E2723) // Dark brown for unselected text
                )
            )
        }
    }
}

@Composable
fun TraitChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF9575CD)) // Fantasy purple for selected
        else BorderStroke(1.dp, Color(0xFFD7CCC8)), // Parchment-like color for unselected
        shadowElevation = if (isSelected) 8.dp else 2.dp, // Stronger shadow for selected chips
        color = Color.Transparent // Transparent color to allow background painter texture to show through
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFDE68A), // Lighter parchment color at top
                            Color(0xFFD7CCC8)  // Darker parchment color at bottom
                        )
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) Color(0xFF4CAF50) // Green for selected text
                    else Color(0xFF3E2723) // Dark brown for unselected text
                )
            )
        }
    }
}



