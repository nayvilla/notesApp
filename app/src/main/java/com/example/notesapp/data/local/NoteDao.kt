/**
 * NoteDao.kt
 *
 * Interfaz que define las operaciones de acceso a datos (DAO) para la entidad Note en la base de datos Room.
 * Utiliza anotaciones de Room para mapear operaciones SQL de forma segura y eficiente.
 *
 * Funcionalidades:
 * - Permite obtener todas las notas ordenadas de forma descendente por ID.
 * - Permite insertar una nueva nota o reemplazarla en caso de conflicto.
 * - Permite actualizar una nota existente.
 * - Permite eliminar una nota específica.
 * - Permite obtener una nota concreta a partir de su ID.
 *
 * Todas las operaciones de modificación (insert, update, delete) se realizan de forma suspendida para soportar corrutinas.
 */

package com.example.notesapp.data.local

import androidx.room.*
import com.example.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?
}
