/**
 * ThemeViewModel.kt
 *
 * ViewModel encargado de gestionar el estado del tema de la aplicación NotesApp.
 * Permite cambiar dinámicamente entre diferentes modos de tema definidos: Light, Dark y VIU.
 *
 * Funcionalidades principales:
 * - Mantiene el modo de tema actual utilizando un StateFlow, permitiendo a los composables observar cambios de manera reactiva.
 * - Proporciona funciones para cambiar directamente el tema (`setTheme`) o alternarlo cíclicamente (`toggleTheme`).
 * - Facilita la implementación de una experiencia personalizada de usuario, adaptando la apariencia visual de la aplicación.
 *
 * Este ViewModel asegura la persistencia en memoria del tema seleccionado mientras la aplicación esté activa.
 */

package com.example.notesapp.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {
    private val _themeMode = MutableStateFlow(AppThemeMode.LIGHT)
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    fun setTheme(theme: AppThemeMode) {
        _themeMode.value = theme
    }

    fun toggleTheme() {
        _themeMode.value = when (_themeMode.value) {
            AppThemeMode.LIGHT -> AppThemeMode.DARK
            AppThemeMode.DARK -> AppThemeMode.VIU
            AppThemeMode.VIU -> AppThemeMode.LIGHT
        }
    }
}
