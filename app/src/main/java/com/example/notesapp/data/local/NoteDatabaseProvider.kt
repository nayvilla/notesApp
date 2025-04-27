/**
 * NoteDatabaseProvider.kt
 *
 * Objeto singleton que gestiona la instancia de la base de datos Room y proporciona acceso centralizado
 * al repositorio de notas (NoteRepository) dentro de la aplicación NotesApp.
 *
 * Funcionalidades:
 * - Asegura que exista una única instancia de NoteDatabase durante todo el ciclo de vida de la aplicación.
 * - Permite obtener la instancia de NoteRepository para interactuar con la capa de datos.
 * - Utiliza el patrón de diseño Singleton y aplica "fallbackToDestructiveMigration" para manejar migraciones destructivas automáticamente.
 *
 * Métodos:
 * - getDatabase(context): Retorna una instancia única de NoteDatabase.
 * - getRepository(context): Retorna una instancia única de NoteRepository basada en la base de datos creada.
 */

package com.example.notesapp.data.local

import android.content.Context
import androidx.room.Room
import com.example.notesapp.data.repository.NoteRepository

object NoteDatabaseProvider {
    private var database: NoteDatabase? = null
    private var repository: NoteRepository? = null

    fun getDatabase(context: Context): NoteDatabase {
        return database ?: Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notes_database"
        ).fallbackToDestructiveMigration().build().also { database = it }
    }

    fun getRepository(context: Context): NoteRepository {
        return repository ?: NoteRepository(getDatabase(context).noteDao()).also { repository = it }
    }
}
