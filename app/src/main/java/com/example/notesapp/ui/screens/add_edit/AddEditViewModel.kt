/**
 * AddEditViewModel.kt
 *
 * Esta clase gestiona la lógica de negocio y el estado de la pantalla de creación y edición de notas en la aplicación NotesApp.
 * Permite actualizar el título, contenido y color de una nota, así como guardar nuevas notas o actualizar notas existentes.
 *
 * Funcionalidades principales:
 * - Cargar los datos de una nota existente mediante su ID.
 * - Actualizar dinámicamente los valores de título, contenido y color en la interfaz.
 * - Generar y guardar una nueva nota o actualizar una nota previa en la base de datos local.
 * - Controlar las fechas de creación y actualización de las notas.
 *
 * Sigue el patrón arquitectónico MVVM y utiliza corrutinas para el acceso asíncrono a los datos mediante un NoteRepository.
 */

package com.example.notesapp.ui.screens.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.Note
import com.example.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.graphics.toArgb

class AddEditViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _content = MutableStateFlow("")
    val content: StateFlow<String> = _content.asStateFlow()

    private val _color = MutableStateFlow(androidx.compose.ui.graphics.Color.White.toArgb())
    val color: StateFlow<Int> = _color.asStateFlow()

    private val _updatedAt = MutableStateFlow("")
    val updatedAt: StateFlow<String> = _updatedAt.asStateFlow()

    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun updateContent(newContent: String) {
        _content.value = newContent
    }

    fun updateColor(newColor: androidx.compose.ui.graphics.Color) {
        _color.value = newColor.toArgb()
    }

    fun loadNoteById(id: Int) {
        viewModelScope.launch {
            repository.getNotes().collect { notes ->
                notes.find { it.id == id }?.let { note ->
                    _title.value = note.title
                    _content.value = note.content
                    _color.value = note.color
                    _updatedAt.value = note.updatedAt
                }
            }
        }
    }

    fun saveNote(noteId: Int?) {
        val currentDate = SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault()).format(Date())

        val note = if (noteId == null || noteId == -1) {
            // Crear nueva nota (sin id)
            Note(
                title = _title.value,
                content = _content.value,
                createdAt = currentDate,
                updatedAt = currentDate,
                color = _color.value
            )
        } else {
            // Editar nota existente
            Note(
                id = noteId,
                title = _title.value,
                content = _content.value,
                createdAt = updatedAt.value,
                updatedAt = currentDate,
                color = _color.value
            )
        }

        viewModelScope.launch {
            if (noteId == null || noteId == -1) {
                repository.addNote(note) // Insertar nuevo
            } else {
                repository.updateNote(note) // Actualizar existente
            }
        }
    }
}
