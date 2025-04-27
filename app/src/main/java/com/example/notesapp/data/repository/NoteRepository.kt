package com.example.notesapp.data.repository

import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    suspend fun addNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteNoteById(id: Int) {
        val note = noteDao.getNoteById(id)
        if (note != null) {
            noteDao.deleteNote(note)
        }
    }

}
