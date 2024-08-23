package com.example.lifespark

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class CharacterViewModel : ViewModel() {
    var selectedRace = mutableStateOf("")
    var selectedArchetype = mutableStateOf("")
    var selectedGender = mutableStateOf("")
    var selectedAlignment = mutableStateOf("")
    var selectedTraits = mutableStateOf<List<String>>(emptyList())
    var backstoryPrompt = mutableStateOf("")

    fun generateNPC(): NPC {
        return NPC(
            name = generateRandomName(selectedRace.value),
            race = selectedRace.value,
            archetype = selectedArchetype.value,
            gender = selectedGender.value,
            alignment = selectedAlignment.value,
            traits = selectedTraits.value,
            backstory = backstoryPrompt.value
        )
    }
}

