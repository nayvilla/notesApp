/**
 * HomeViewModelFactory.kt
 *
 * Esta clase define la fábrica (Factory) para la creación del HomeViewModel.
 * Permite inyectar una instancia del repositorio de notas (NoteRepository) en el ViewModel,
 * siguiendo el principio de inversión de dependencias en el patrón arquitectónico MVVM.
 *
 * Funcionalidades principales:
 * - Crear instancias de HomeViewModel con el repositorio necesario.
 * - Gestionar errores en caso de solicitar la creación de un ViewModel desconocido.
 *
 * Se utiliza para integrar correctamente la arquitectura de ViewModelProvider en la pantalla principal (HomeScreen).
 */


package com.example.notesapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.repository.NoteRepository

class HomeViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
