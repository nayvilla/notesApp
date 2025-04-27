/**
 * AddEditViewModelFactory.kt
 *
 * Esta clase implementa el patrón Factory para instanciar el ViewModel AddEditViewModel en la pantalla de creación y edición de notas.
 * Se encarga de proporcionar la dependencia del NoteRepository al ViewModel de forma segura y desacoplada.
 *
 * Funcionalidades principales:
 * - Crear instancias de AddEditViewModel utilizando el repositorio proporcionado.
 * - Asegurar que la creación de ViewModels esté controlada mediante el patrón de fábrica.
 *
 * Forma parte de la implementación del patrón arquitectónico MVVM en la aplicación NotesApp.
 */

package com.example.notesapp.ui.screens.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.repository.NoteRepository

class AddEditViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditViewModel::class.java)) {
            return AddEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
