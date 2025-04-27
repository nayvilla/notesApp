package com.example.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.theme.NotesAppTheme
import com.example.notesapp.theme.ThemeViewModel
import com.example.notesapp.ui.screens.add_edit.AddEditScreen
import com.example.notesapp.ui.screens.detail.DetailScreen
import com.example.notesapp.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val themeViewModel: ThemeViewModel = viewModel() // Instanciar el ViewModel de tema
    val appTheme = themeViewModel.themeMode.collectAsState()

    NotesAppTheme(appTheme = appTheme.value) { // Aplicar el tema global
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(navController, themeViewModel) // Pasar ThemeViewModel a HomeScreen
            }
            composable("add_edit/{noteId}") { backStackEntry ->
                val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull() ?: -1
                AddEditScreen(navController, noteId)
            }
            composable("detail/{noteId}") { backStackEntry ->
                val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull() ?: -1
                DetailScreen(navController, noteId)
            }
        }
    }
}
