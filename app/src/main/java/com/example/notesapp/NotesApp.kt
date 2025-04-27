/**
 * NotesApp.kt
 *
 * Archivo principal de la aplicación que configura el tema general y carga el sistema de navegación entre pantallas.
 *
 * Funcionalidades:
 * - Aplica el tema global utilizando NotesAppTheme.
 * - Define una Surface que utiliza el color de fondo del tema actual.
 * - Inicia la navegación entre pantallas mediante AppNavigation.
 *
 * Esta función actúa como punto de entrada para la estructura visual de la aplicación.
 */

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
