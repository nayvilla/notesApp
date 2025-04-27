/**
 * Note.kt
 *
 * Entidad de datos que representa una nota dentro de la aplicación NotesApp.
 * Utiliza anotaciones de Room para definir su correspondencia con una tabla en la base de datos local.
 *
 * Campos:
 * - id: Identificador único de la nota (clave primaria, autogenerado).
 * - title: Título breve de la nota.
 * - content: Contenido completo de la nota.
 * - createdAt: Fecha y hora de creación de la nota.
 * - updatedAt: Fecha y hora de la última actualización de la nota.
 * - color: Color asociado a la nota, almacenado como valor entero ARGB.
 *
 * Esta entidad permite almacenar, consultar y gestionar notas de manera estructurada en la base de datos local usando Room.
 */

package com.example.notesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: String,    // Fecha de creación
    val updatedAt: String,    // Fecha de actualización
    val color: Int
)
