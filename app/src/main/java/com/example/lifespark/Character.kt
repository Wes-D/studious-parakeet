package com.example.lifespark

data class Character(
    val name: String,
    val race: String,
    val archetype: String,
    val gender: String,
    val alignment: String,
    val traits: List<String>,
    val backstory: String
)

