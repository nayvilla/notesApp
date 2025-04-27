package com.example.notesapp.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notesapp.data.local.NoteDatabaseProvider
import com.example.notesapp.data.model.Note
import com.example.notesapp.ui.components.CustomButton
import com.example.notesapp.ui.components.NoteCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val repository = NoteDatabaseProvider.getRepository(context)
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(repository)
    )

    var searchQuery by remember { mutableStateOf("") }
    val notes by viewModel.notes.collectAsState()

    var selectedNote by remember { mutableStateOf<Note?>(null) }
    var showOptionsDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_edit/-1") }, // "-1" es para nueva nota
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Nota")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Mis Notas",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                placeholder = { Text("Buscar...", style = MaterialTheme.typography.bodyLarge) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (notes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay notas aún.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        notes.filter {
                            it.title.contains(searchQuery, ignoreCase = true) ||
                                    it.content.contains(searchQuery, ignoreCase = true)
                        }
                    ) { note ->

                        val rawDate = when {
                            note.createdAt.isBlank() && note.updatedAt.isBlank() -> {
                                "Sin fecha registrada"
                            }
                            note.createdAt == note.updatedAt -> {
                                note.createdAt
                            }
                            else -> {
                                note.updatedAt
                            }
                        }

                        val dateLabel = rawDate.take(11)

                        NoteCard(
                            title = note.title,
                            content = note.content,
                            date = dateLabel,
                            color = note.color,
                            onClick = {
                                selectedNote = note
                                showOptionsDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    if (showOptionsDialog && selectedNote != null) {
        AlertDialog(
            onDismissRequest = { showOptionsDialog = false },
            shape = RoundedCornerShape(20.dp),
            title = {
                Text(
                    text = "¿Qué deseas hacer?",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomButton(
                        text = "Ver Detalles",
                        leadingIcon = Icons.Default.Search, // Icono de lupa por ejemplo
                        onClick = {
                            navController.navigate("detail/${selectedNote!!.id}")
                            showOptionsDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    CustomButton(
                        text = "Editar Nota",
                        leadingIcon = Icons.Default.Edit, // Icono de editar
                        onClick = {
                            navController.navigate("add_edit/${selectedNote!!.id}")
                            showOptionsDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    CustomButton(
                        text = "Eliminar Nota",
                        leadingIcon = Icons.Default.Delete, // Icono de eliminar
                        onClick = {
                            viewModel.deleteNoteById(selectedNote!!.id)
                            showOptionsDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                // No mostrar botón confirm
            },
            dismissButton = {
                TextButton(
                    onClick = { showOptionsDialog = false }
                ) {
                    Text(
                        text = "Cancelar",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    }
}
