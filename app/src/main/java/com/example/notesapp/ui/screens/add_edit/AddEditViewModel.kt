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

    fun saveNote() {
        val currentDate = SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault()).format(Date())

        // Actualiza la fecha de actualización en el ViewModel
        _updatedAt.value = currentDate

        val note = Note(
            title = _title.value,
            content = _content.value,
            createdAt = currentDate,
            updatedAt = currentDate,
            color = _color.value
        )

        viewModelScope.launch {
            repository.addNote(note)
        }
    }
}
