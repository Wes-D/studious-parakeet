package com.example.lifespark

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NPC(
    val name: String,
    val race: String,
    val archetype: String,
    val gender: String,
    val alignment: String,
    val traits: List<String>,
    val backstory: String
) : Parcelable

