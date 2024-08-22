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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterBasicInfoScreen(
    onNext: () -> Unit,
    onRaceSelected: (String) -> Unit,
    onArchetypeSelected: (String) -> Unit,
    onGenderSelected: (String) -> Unit,
    onAlignmentSelected: (String) -> Unit,
    selectedRace: String?,
    selectedArchetype: String?,
    selectedGender: String?,
    selectedAlignment: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Your NPC - Step 1: Basic Info") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Progress Indicator
            Text("Step 1 of 2", style = MaterialTheme.typography.bodyMedium)

            // Race Selection Dropdown
            SearchableDropdownMenu(
                label = "Race",
                options = listOf(
                    "Aarakocra", "Aasimar (Fallen)", "Aasimar (Protector)", "Aasimar (Scourge)", "Bugbear", "Centaur", "Changling",
                    "Dragonborn", "Dwarf (Duergar)", "Dwarf (Hill)", "Dwarf (Mountain)", "Elf (Drow)", "Elf (Eladrin)",
                    "Elf (Moon)", "Elf (Sea)", "Elf (Shadar-Kai)", "Elf (Sun)", "Elf (Wood)", "Firbolg", "Genasi (Air)",
                    "Genasi (Earth)", "Genasi (Fire)", "Genasi (Water)", "Githyanki", "Githzerai", "Gnome (Forest)",
                    "Gnome (Rock)", "Gnome (Svirfneblin)", "Goblin", "Goliath", "Half-Elf (Drow)", "Half-Elf (Eladrin)",
                    "Half-Elf (Moon)", "Half-Elf (Sea)", "Half-Elf (Shadar-Kai)", "Half-Elf (Sun)", "Half-Elf (Wood)",
                    "Half-Orc", "Halfling (Ghostwise)", "Halfling (Lightfoot)", "Halfling (Lotusden)", "Halfling (Stout)",
                    "Hobgoblin", "Human", "Kalashtar", "Kenku", "Kobold", "Leonin", "Lizardfolk", "Loxodon", "Minotaur",
                    "Orc", "Satyr", "Tabaxi", "Tiefling", "Tortle", "Triton", "Warforged", "Yuan-ti (Pureblood)"
                ),
                selectedOption = selectedRace,
                onOptionSelected = onRaceSelected
            )

            // Archetype Selection Dropdown
            SearchableDropdownMenu(
                label = "Archetype",
                options = listOf(
                    "Abjurer", "Acolyte", "Apprentice wizard", "Archer", "Artisan", "Assassin", "Bard", "Bandit", "Bandit captain",
                    "Berserker", "Blackguard", "Champion", "Conjurer", "Diviner", "Druid", "Enchanter", "Evoker", "Gladiator",
                    "Guard", "Healer", "Host", "Illusionist", "Innocent", "Knight", "Mage", "Martial arts adept", "Master thief",
                    "Merchant", "Necromancer", "Noble", "Pilgrim", "Priest", "Royalty", "Sage", "Scout", "Shadow", "Spy",
                    "Swashbuckler", "Thug", "Transmuter", "Tribal warrior", "Trickster", "Vagabond", "Veteran", "Villager",
                    "War priest", "Warlock (Archfey)", "Warlock (Fiend)", "Warlock (Great Old One)"
                ),
                selectedOption = selectedArchetype,
                onOptionSelected = onArchetypeSelected
            )

            // Gender Selection Chips
            GenderSelectionChips(
                selectedGender = selectedGender,
                onGenderSelected = onGenderSelected
            )
/*
            // Alignment Grid
            AlignmentSelectionGrid(
                selectedAlignment = selectedAlignment,
                onAlignmentSelected = onAlignmentSelected
            )
           */
            // Next Button
            Button(onClick = onNext) {
                Text("Next")
            }
        }
    }
}

@Composable
fun SearchableDropdownMenu(
    label: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val filteredOptions = options.filter { it.contains(searchText, ignoreCase = true) }

    Column {
        // Search TextField
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown Button
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(selectedOption ?: "Select $label")
            }

            // Dropdown Menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                filteredOptions.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        text = { Text(text = option) }
                    )
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
    var customGender by remember { mutableStateOf("") }

    Column {
        Text("Select Gender")

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalArrangement = Arrangement.Top
        ) {
            genderOptions.forEach { gender ->
                val isSelected = selectedGender == gender

                CustomChip(
                    text = gender,
                    isSelected = isSelected,
                    onClick = {
                        if (gender == "Custom") {
                            onGenderSelected(customGender)
                        } else {
                            onGenderSelected(gender)
                        }
                    }
                )
            }
        }

        // Custom Gender Input
        if (selectedGender == "Custom") {
            TextField(
                value = customGender,
                onValueChange = { customGender = it },
                label = { Text("Enter Custom Gender") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CustomChip(
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
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    }
}

