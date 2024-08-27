package com.example.lifespark.ui.screens

import com.example.lifespark.R
import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterBasicInfoScreen(
    viewModel: CharacterViewModel,
    onNext: () -> Unit
) {
    // Background Image
    val backgroundPainter: Painter = painterResource(id = R.drawable.background_parchment)
    // Access the ViewModel's state
    val selectedRace = viewModel.selectedRace.value
    val selectedArchetype = viewModel.selectedArchetype.value
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
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                    /*
                    Image(
                        painter = painterResource(id = R.drawable.dragon_stamp), // Your decorative image
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp) // Adjust size as needed
                            .padding(start = 4.dp, end = 16.dp)
                    )
                    */

                // Decorative or simple divider below the TopAppBar
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp),
                    thickness = 4.dp,
                    color = Color(0xFF795548) // Use a decorative color that matches the theme
                )

                // Progress Indicator
                //Text("Step 1 of 2", style = MaterialTheme.typography.bodyLarge)

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
    // Default value if no option is selected
    val displayedOption = selectedOption ?: "Random"

    Box(modifier = Modifier.clickable { focusManager.clearFocus() }) {
        Column {

            // Label and currently selected option
            Text(text = label, style = MaterialTheme.typography.bodyMedium)

            OutlinedTextField(
                value = displayedOption,
                onValueChange = { },
                readOnly = true,
                interactionSource = remember { MutableInteractionSource() },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { dialogOpen = true },
                trailingIcon = {
                    IconButton(onClick = { dialogOpen = true }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                },
                placeholder = { Text(text = "Select $label") } // This could also be "Random"
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
        modifier = Modifier
            .fillMaxWidth(),
            //.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Alignment",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        for (row in alignments.chunked(3)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { alignment ->
                    AlignmentBox(
                        alignment = alignment,
                        isSelected = selectedAlignment == alignment,
                        onAlignmentSelected = onAlignmentSelected,
                        imageResId = getAlignmentImage(alignment),
                        modifier = Modifier
                            .weight(1f) // Distribute evenly in the row
                            .aspectRatio(1f)
                    )
                }
                // If the row has less than 3 items, fill the remaining space with empty boxes
                if (row.size < 3) {
                    repeat(3 - row.size) {
                        Spacer(modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f))
                    }
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
    // Determine background color based on alignment type
    val backgroundColor = when {
        alignment.contains("Good") -> Color(0xFF4CAF50) // Green for good alignments
        alignment.contains("Evil") -> Color(0xFFF44336) // Red for evil alignments
        else -> Color(0xFF9E9E9E) // Grey for neutral alignments
    }

    // Determine border color based on alignment type
    val borderColor = when {
        alignment.contains("Good") -> Color.Cyan
        alignment.contains("Evil") -> Color(0xFF9C27B0) // Purple for evil alignments
        else -> Color.White // White for neutral alignments
    }

    // Animate border color and width
    val animatedBorderColor by animateColorAsState(
        targetValue = if (isSelected) borderColor else Color.Black,
        animationSpec = tween(durationMillis = 500), label = ""
    )
    val animatedBorderWidth by animateDpAsState(
        targetValue = if (isSelected) 4.dp else 1.dp,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Surface(
        modifier = modifier
            .clickable { onAlignmentSelected(alignment) }
            .border(
                BorderStroke(animatedBorderWidth, animatedBorderColor),
                RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        color = if (isSelected) backgroundColor.copy(alpha = 0.6f) else backgroundColor, // Slightly dim background when selected
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
                    modifier = Modifier
                        .fillMaxSize() // image scaled to 100% of box size
                )

                // Alignment Label if wanted
                /*Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = alignment,
                    color = if (isSelected) Color.Black else Color.Gray,
                    textAlign = TextAlign.Center
                )*/
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