package com.example.notesapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.notesapp.navigation.AppNavigation
import com.example.notesapp.theme.AppThemeMode
import com.example.notesapp.theme.NotesAppTheme

@Composable
fun NotesApp() {
    NotesAppTheme (appTheme = AppThemeMode.LIGHT) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation() // Llamamos a la navegación de pantallas
        }
    }
}
