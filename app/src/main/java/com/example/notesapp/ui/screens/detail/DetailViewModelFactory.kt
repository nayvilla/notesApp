/**
 * DetailViewModelFactory.kt
 *
 * Esta clase implementa una fábrica de ViewModels que proporciona instancias de DetailViewModel.
 * Se utiliza para inyectar las dependencias necesarias (NoteRepository y noteId) en el DetailViewModel.
 *
 * Funcionalidades principales:
 * - Crear y devolver instancias de DetailViewModel de forma controlada.
 * - Facilitar el cumplimiento del patrón MVVM asegurando la correcta provisión de dependencias.
 *
 * Se emplea la interfaz ViewModelProvider.Factory para integrar la fábrica con el ciclo de vida de Compose.
 */

package com.example.notesapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.repository.NoteRepository

class DetailViewModelFactory(
    private val repository: NoteRepository,
    private val noteId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository, noteId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
