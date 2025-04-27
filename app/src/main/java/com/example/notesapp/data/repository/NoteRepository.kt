/**
 * NoteRepository.kt
 *
 * Clase que implementa el patrón Repository para la gestión de datos relacionados con las notas en la aplicación NotesApp.
 * Actúa como intermediario entre el acceso a la base de datos local (NoteDao) y las capas de ViewModel/Interfaz de Usuario.
 *
 * Funcionalidades principales:
 * - Proporciona un flujo reactivo de la lista de notas almacenadas en la base de datos local.
 * - Permite agregar, actualizar y eliminar notas de manera eficiente utilizando corrutinas.
 * - Encapsula la lógica de negocio para la eliminación de una nota específica por su identificador (`deleteNoteById`).
 *
 * Este repositorio facilita la separación de responsabilidades y promueve una arquitectura limpia (MVVM) en la aplicación.
 */

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
