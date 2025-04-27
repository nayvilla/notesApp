/**
 * NoteDatabase.kt
 *
 * Clase abstracta que define la configuración de la base de datos Room para la aplicación NotesApp.
 * Está anotada con @Database e incluye la entidad "Note" y la versión actual de la base de datos.
 *
 * Funcionalidades:
 * - Define la base de datos local utilizando Room.
 * - Proporciona acceso al objeto de acceso a datos (DAO) NoteDao.
 * - Administra automáticamente la creación y actualización de la base de datos.
 *
 * Métodos:
 * - noteDao(): Retorna la interfaz NoteDao para realizar operaciones CRUD sobre las notas almacenadas.
 */

package com.example.notesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.data.model.Note

@Database(entities = [Note::class], version = 2)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
