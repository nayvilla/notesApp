/**
 * MainActivity.kt
 *
 * Clase principal de la aplicación que extiende de ComponentActivity.
 *
 * Funcionalidades:
 * - Configura la actividad principal.
 * - Establece el contenido de la interfaz de usuario utilizando Jetpack Compose.
 * - Inicializa la aplicación llamando a NotesApp().
 *
 * Esta clase sirve como punto de inicio para el ciclo de vida de la aplicación Android.
 */

package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesApp() // Llamas a tu aplicación Compose
        }
    }
}
