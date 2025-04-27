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
