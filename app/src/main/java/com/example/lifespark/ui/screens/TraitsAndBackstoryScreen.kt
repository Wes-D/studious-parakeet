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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
                //horizontalAlignment = Alignment.CenterHorizontally,
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
                    style = MaterialTheme.typography.bodyMedium
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
    // Gender options and state variables
    val genderOptions = listOf("Female", "Male", "Non-binary", "Custom")
    var customGender by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // Ensure default gender selection is the first item unless a gender is already selected
    val currentGender = remember {
        mutableStateOf(selectedGender ?: genderOptions.first())
    }

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
                val isSelected = when {
                    gender == "Custom" && customGender.isNotEmpty() -> currentGender.value == customGender
                    else -> currentGender.value == gender
                }

                GenderChip(
                    text = if (gender == "Custom" && customGender.isNotEmpty()) "Custom: $customGender" else gender,
                    isSelected = isSelected,
                    onClick = {
                        if (gender == "Custom") {
                            showDialog = true
                        } else {
                            currentGender.value = gender
                            onGenderSelected(gender)
                        }
                    }
                )
            }
        }

        if (showDialog) {
            CustomGenderDialog(
                customGender = customGender,
                onGenderConfirm = { newGender ->
                    customGender = newGender
                    currentGender.value = newGender // Set custom gender as selected
                    onGenderSelected(newGender)
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun CustomGenderDialog(
    customGender: String,
    onGenderConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var textState by remember { mutableStateOf(customGender) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Enter Custom Gender") },
        text = {
            OutlinedTextField(
                value = textState,
                onValueChange = { newValue ->
                    val filteredText = newValue.filter { it.isLetter() || it == '-' || it == '\'' || it.isWhitespace() }
                    if (filteredText.length <= 20) {
                        textState = filteredText
                    }
                },
                placeholder = { Text("Enter custom gender") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (textState.isNotBlank()) {
                            onGenderConfirm(textState)
                        }
                    }
                )
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (textState.isNotBlank()) {
                        onGenderConfirm(textState)
                    }
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
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
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF9575CD)) // Fantasy purple for selected
        else BorderStroke(1.dp, Color(0xFFD7CCC8)), // Parchment-like color for unselected
        shadowElevation = if (isSelected) 8.dp else 2.dp,
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
                    color = if (isSelected) Color(0xFF4CAF50) else Color(0xFF3E2723)
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



