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
import com.example.lifespark.ui.screens.CharacterOverviewScreen
import com.example.lifespark.ui.screens.HomeScreen
import com.example.lifespark.ui.screens.TraitsAndBackstoryScreen
import com.example.lifespark.ui.theme.LifeSparkTheme
import androidx.lifecycle.viewmodel.compose.viewModel

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
    // Create and share the ViewModel
    val characterViewModel: CharacterViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onCreateCharacter = { navController.navigate("character_basic_info") },
                onLoadCharacter = { navController.navigate("load_character") }
            )
        }

        composable("character_basic_info") {
            CharacterBasicInfoScreen(
                viewModel = characterViewModel,  // Pass the ViewModel to the screen
                onNext = { navController.navigate("traits_backstory") }
            )
        }

        composable("traits_backstory") {
            TraitsAndBackstoryScreen(
                viewModel = characterViewModel,  // Pass the ViewModel to the screen
                onGenerateNPC = {
                    // Generate NPC using the ViewModel's state
                    val generatedNPC = characterViewModel.generateNPC()

                    // Pass the NPC to the summary screen
                    navController.navigate("npc_summary") {
                        navController.currentBackStackEntry?.arguments?.putParcelable("npc", generatedNPC)
                    }
                }
            )
        }

        composable("npc_summary") {
            val npc = navController.previousBackStackEntry?.arguments?.getParcelable<NPC>("npc")
            npc?.let {
                CharacterOverviewScreen(
                    npc = it,
                    onEditCharacter = { navController.popBackStack() },
                    onSaveCharacter = { /* Handle save */ },
                    onExportCharacter = { /* Handle export */ }
                )
            }
        }


        // Load Character Screen
        composable("load_character") {
            // TODO: Implement LoadCharacterScreen
        }
    }
}

