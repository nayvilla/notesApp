package com.example.notesapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.Note
import com.example.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: NoteRepository,
    private val noteId: Int
) : ViewModel() {

    private val _note = MutableStateFlow(
        Note(
            id = 0,
            title = "",
            content = "",
            createdAt = "",
            updatedAt = "",
            color = androidx.compose.ui.graphics.Color.LightGray.value.toInt()
        )
    )
    val note: StateFlow<Note> = _note.asStateFlow()

    init {
        loadNote()
    }

    private fun loadNote() {
        viewModelScope.launch {
            repository.getNotes().collect { notes ->
                notes.find { it.id == noteId }?.let { foundNote ->
                    _note.value = foundNote
                }
            }
        }
    }
}
