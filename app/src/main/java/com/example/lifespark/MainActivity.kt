package com.example.lifespark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lifespark.ui.screens.CharacterBasicInfoScreen
import com.example.lifespark.ui.screens.HomeScreen
import com.example.lifespark.ui.screens.TraitsAndBackstoryScreen
import com.example.lifespark.ui.theme.LifeSparkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeSparkTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Home Screen
        composable("home") {
            HomeScreen(
                onCreateCharacter = { navController.navigate("character_basic_info") },
                onLoadCharacter = { navController.navigate("load_character") }
            )
        }

        // Character Basic Info Screen (Page 1)
        composable("character_basic_info") {
            CharacterBasicInfoScreen(
                selectedRace = /* state for race */,
                onRaceSelected = { /* handle race selection */ },
                selectedArchetype = /* state for archetype */,
                onArchetypeSelected = { /* handle archetype selection */ },
                selectedGender = /* state for gender */,
                onGenderSelected = { /* handle gender selection */ },
                selectedAlignment = /* state for alignment */,
                onAlignmentSelected = { /* handle alignment selection */ },
                onNext = {
                    navController.navigate("traits_backstory")
                }
            )
        }

        // Traits and Backstory Screen (Page 2)
        composable("traits_backstory") {
            TraitsAndBackstoryScreen(
                selectedTraits = /* state for selected traits */,
                onTraitSelected = { /* handle trait selection */ },
                onTraitDeselected = { /* handle trait deselection */ },
                backstoryPrompt = /* state for backstory prompt */,
                onBackstoryChanged = { /* handle backstory input */ },
                onGenerateNPC = {
                    // Navigate to the NPC summary screen after generation
                    navController.navigate("npc_summary")
                }
            )
        }

        // Placeholder for NPC Summary Screen
        composable("npc_summary") {
            // This will display the final generated NPC
            // TODO: Implement NPCSummaryScreen
        }

        // Load Character Screen
        composable("load_character") {
            // TODO: Implement LoadCharacterScreen
        }
    }
}

