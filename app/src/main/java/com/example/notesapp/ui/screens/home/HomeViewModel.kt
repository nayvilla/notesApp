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
