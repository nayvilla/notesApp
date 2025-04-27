/**
 * HomeViewModel.kt
 *
 * Esta clase implementa el ViewModel correspondiente a la pantalla principal (HomeScreen).
 * Se encarga de gestionar la lista de notas y el estado del tema de la aplicación (claro, oscuro, VIU).
 *
 * Funcionalidades principales:
 * - Obtener y actualizar en tiempo real la lista de notas desde el repositorio.
 * - Eliminar una nota específica mediante su ID.
 * - Gestionar el cambio dinámico de tema en la aplicación.
 *
 * Se implementa siguiendo el patrón arquitectónico MVVM, utilizando corrutinas y StateFlow
 * para la actualización reactiva de la interfaz de usuario.
 */


package com.example.notesapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.Note
import com.example.notesapp.data.repository.NoteRepository
import com.example.notesapp.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private val _themeMode = MutableStateFlow(AppThemeMode.LIGHT) // manejo de tema
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            repository.getNotes().collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun deleteNoteById(id: Int) {
        viewModelScope.launch {
            repository.deleteNoteById(id)
            getNotes() // Recargar la lista después de eliminar
        }
    }

    fun toggleTheme() { // Cambiar tema dinámicamente
        _themeMode.value = when (_themeMode.value) {
            AppThemeMode.LIGHT -> AppThemeMode.DARK
            AppThemeMode.DARK -> AppThemeMode.VIU
            AppThemeMode.VIU -> AppThemeMode.LIGHT
        }
    }

    fun setTheme(theme: AppThemeMode) {
        _themeMode.value = theme
    }

}
