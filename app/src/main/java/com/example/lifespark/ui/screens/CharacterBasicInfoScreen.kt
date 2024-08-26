package com.example.lifespark.ui.screens

import com.example.lifespark.R
import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import com.example.lifespark.CharacterViewModel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterBasicInfoScreen(
    viewModel: CharacterViewModel,
    onNext: () -> Unit
) {
    // Access the ViewModel's state
    val selectedRace = viewModel.selectedRace.value
    val selectedArchetype = viewModel.selectedArchetype.value
    val selectedGender = viewModel.selectedGender.value
    val selectedAlignment = viewModel.selectedAlignment.value
    val raceOptions = listOf(
        "Aarakocra", "Aasimar (Fallen)", "Aasimar (Protector)", "Aasimar (Scourge)", "Bugbear", "Centaur", "Changling",
        "Dragonborn", "Dwarf (Duergar)", "Dwarf (Hill)", "Dwarf (Mountain)", "Elf (Drow)", "Elf (Eladrin)",
        "Elf (Moon)", "Elf (Sea)", "Elf (Shadar-Kai)", "Elf (Sun)", "Elf (Wood)", "Firbolg", "Genasi (Air)",
        "Genasi (Earth)", "Genasi (Fire)", "Genasi (Water)", "Githyanki", "Githzerai", "Gnome (Forest)",
        "Gnome (Rock)", "Gnome (Svirfneblin)", "Goblin", "Goliath", "Half-Elf (Drow)", "Half-Elf (Eladrin)",
        "Half-Elf (Moon)", "Half-Elf (Sea)", "Half-Elf (Shadar-Kai)", "Half-Elf (Sun)", "Half-Elf (Wood)",
        "Half-Orc", "Halfling (Ghostwise)", "Halfling (Lightfoot)", "Halfling (Lotusden)", "Halfling (Stout)",
        "Hobgoblin", "Human", "Kalashtar", "Kenku", "Kobold", "Leonin", "Lizardfolk", "Loxodon", "Minotaur",
        "Orc", "Satyr", "Tabaxi", "Tiefling", "Tortle", "Triton", "Warforged", "Yuan-ti (Pureblood)"
    )
    val archetypeOptions = listOf(
        "Abjurer", "Acolyte", "Apprentice wizard", "Archer", "Artisan", "Assassin", "Bard", "Bandit", "Bandit captain",
        "Berserker", "Blackguard", "Champion", "Conjurer", "Diviner", "Druid", "Enchanter", "Evoker", "Gladiator",
        "Guard", "Healer", "Host", "Illusionist", "Innocent", "Knight", "Mage", "Martial arts adept", "Master thief",
        "Merchant", "Necromancer", "Noble", "Pilgrim", "Priest", "Royalty", "Sage", "Scout", "Shadow", "Spy",
        "Swashbuckler", "Thug", "Transmuter", "Tribal warrior", "Trickster", "Vagabond", "Veteran", "Villager",
        "War priest", "Warlock (Archfey)", "Warlock (Fiend)", "Warlock (Great Old One)"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Your NPC - Step 1: Basic Info") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Progress Indicator
            Text("Step 1 of 2", style = MaterialTheme.typography.bodyMedium)

            // Race Dropdown with Dialog
            DropdownMenuWithSearchDialog(
                options = raceOptions,
                selectedOption = selectedRace,
                onOptionSelected = { viewModel.selectedRace.value = it },
                label = "Race"
            )

            // Archetype Dropdown with Dialog
            DropdownMenuWithSearchDialog(
                options = archetypeOptions,
                selectedOption = selectedArchetype,
                onOptionSelected = { viewModel.selectedArchetype.value = it },
                label = "Archetype"
            )

            // Gender Selection Chips
            GenderSelectionChips(
                selectedGender = selectedGender,
                onGenderSelected = { selectedGender ->
                    viewModel.selectedGender.value = selectedGender }
            )

            // Alignment Grid
            AlignmentGrid(
                selectedAlignment = selectedAlignment,
                onAlignmentSelected = { selectedAlignment ->
                    viewModel.selectedAlignment.value = selectedAlignment
                }
            )

            // Next Button
            Button(onClick = onNext) {
                Text("Next")
            }
        }
    }
}

@Composable
fun DropdownMenuWithSearchDialog(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    label: String
) {
    var dialogOpen by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val filteredOptions = options.filter { it.contains(searchQuery, ignoreCase = true) }
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.clickable { focusManager.clearFocus() }) {
        Column {

            // Label and currently selected option
            Text(text = label, style = MaterialTheme.typography.bodyMedium)

            OutlinedTextField(
                value = selectedOption,
                onValueChange = { },
                label = { Text("Selected $label") },
                readOnly = true,
                interactionSource = remember { MutableInteractionSource() },
                modifier = Modifier
                    .fillMaxWidth().clickable { dialogOpen = true },
                trailingIcon = {
                    IconButton(onClick = { dialogOpen = true }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )

            if (dialogOpen) {
                AlertDialog(
                    onDismissRequest = { dialogOpen = false },
                    title = { Text("Select $label") },
                    text = {
                        Column {
                            // Search box inside the dialog
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                label = { Text("Search $label") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                )
                            )

                            // Filtered options inside the dialog
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 400.dp)
                            ) {
                                items(filteredOptions) { option ->
                                    TextButton(
                                        onClick = {
                                            onOptionSelected(option)
                                            dialogOpen = false
                                            searchQuery = ""  // Reset search when an option is selected
                                        }
                                    ) {
                                        Text(text = option)
                                    }
                                }
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { dialogOpen = false }) {
                            Text("Close")
                        }
                    }
                )
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun GenderSelectionChips(
    selectedGender: String?,
    onGenderSelected: (String) -> Unit
) {
    val genderOptions = listOf("Male", "Female", "Non-binary", "Custom")

    // State for custom gender input
    var customGender by remember { mutableStateOf("") }
    var isCustomSelected by remember { mutableStateOf(selectedGender == "Custom") }
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
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        tonalElevation = if (isSelected) 8.dp else 0.dp // Add elevation when selected
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun CustomChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun AlignmentGrid(
    selectedAlignment: String?,
    onAlignmentSelected: (String) -> Unit
) {
    val alignments = listOf(
        "Lawful Good", "Neutral Good", "Chaotic Good",
        "Lawful Neutral", "True Neutral", "Chaotic Neutral",
        "Lawful Evil", "Neutral Evil", "Chaotic Evil"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Alignment",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        for (row in alignments.chunked(3)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for (alignment in row) {
                    AlignmentBox(
                        alignment = alignment,
                        isSelected = selectedAlignment == alignment,
                        onAlignmentSelected = onAlignmentSelected,
                        imageResId = getAlignmentImage(alignment),
                        modifier = Modifier.weight(1f) // Distribute evenly in the row
                    )
                }
            }
        }
    }
}

@Composable
fun AlignmentBox(
    alignment: String,
    isSelected: Boolean,
    onAlignmentSelected: (String) -> Unit,
    imageResId: Int, // Image resource ID for the alignment
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onAlignmentSelected(alignment) },
        shape = RoundedCornerShape(8.dp),
        color = if (isSelected) Color.LightGray else Color.White,
        shadowElevation = if (isSelected) 10.dp else 2.dp,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Display the image corresponding to the alignment
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = alignment,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize(0.6f) //image scaled to 60% of box size
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = alignment,
                    color = if (isSelected) Color.Black else Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// Function to map each alignment to its respective image
fun getAlignmentImage(alignment: String): Int {
    return when (alignment) {
        "Lawful Good" -> R.drawable.alignment_lawful_good
        "Neutral Good" -> R.drawable.alignment_neutral_good
        "Chaotic Good" -> R.drawable.alignment_chaotic_good
        "Lawful Neutral" -> R.drawable.alignment_lawful_neutral
        "True Neutral" -> R.drawable.alignment_true_neutral
        "Chaotic Neutral" -> R.drawable.alignment_chaotic_neutral
        "Lawful Evil" -> R.drawable.alignment_lawful_evil
        "Neutral Evil" -> R.drawable.alignment_neutral_evil
        "Chaotic Evil" -> R.drawable.alignment_chaotic_evil
        else -> R.drawable.alignment_default // Fallback image
    }
}