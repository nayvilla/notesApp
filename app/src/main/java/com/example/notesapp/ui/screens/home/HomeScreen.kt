package com.example.notesapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notesapp.R
import com.example.notesapp.data.local.NoteDatabaseProvider
import com.example.notesapp.data.model.Note
import com.example.notesapp.theme.AppThemeMode
import com.example.notesapp.theme.ThemeViewModel
import com.example.notesapp.ui.components.CustomButton
import com.example.notesapp.ui.components.NoteCard
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    themeViewModel: ThemeViewModel
) {
    val context = LocalContext.current
    val repository = NoteDatabaseProvider.getRepository(context)
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(repository))

    var searchQuery by remember { mutableStateOf("") }
    val notes by viewModel.notes.collectAsState()

    var selectedNote by remember { mutableStateOf<Note?>(null) }
    var showOptionsDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedColorFilter by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_edit/-1") },
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
            // Sección de título y botón de configuración de tema
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.title_my_notes),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Cambiar Tema"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Claro   ☀️") },
                            onClick = {
                                themeViewModel.setTheme(AppThemeMode.LIGHT)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Oscuro   \uD83C\uDF19") },
                            onClick = {
                                themeViewModel.setTheme(AppThemeMode.DARK)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("VIU  \uD83D\uDCDA") },
                            onClick = {
                                themeViewModel.setTheme(AppThemeMode.VIU)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                placeholder = { Text(stringResource(id = R.string.hint_search), style = MaterialTheme.typography.bodyLarge) },
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de filtros de colores
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    FilterChip(
                        selected = selectedColorFilter == null,
                        onClick = { selectedColorFilter = null },
                        label = { Text("Todos") },
                        shape = RoundedCornerShape(50),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                }

                // Definición de colores como enteros firmados
                val colorOptions = listOf(
                    0xFFFFFF00.toInt(), // Amarillo
                    0xFF00FFFF.toInt(), // Cian
                    0xFF00FF00.toInt(), // Verde
                    0xFFFF0000.toInt(), // Rojo
                    0xFF0000FF.toInt(),  // Azul
                    0xFFFFFFFF.toInt() // Blanco
                )

                items(colorOptions.size) { index ->
                    val colorInt = colorOptions[index]
                    FilterChip(
                        selected = selectedColorFilter == colorInt,
                        onClick = {
                            selectedColorFilter = if (selectedColorFilter == colorInt) null else colorInt
                        },
                        label = {},
                        leadingIcon = {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color(colorInt), shape = RoundedCornerShape(50))
                            )
                        },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .width(40.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(colorInt),
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Visualización de notas
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
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    val filteredNotes = notes.filter { note ->
                        (searchQuery.isBlank() || note.title.contains(searchQuery, ignoreCase = true) || note.content.contains(searchQuery, ignoreCase = true)) &&
                                (selectedColorFilter == null || note.color == selectedColorFilter)
                    }

                    items(filteredNotes.size) { index ->
                        val note = filteredNotes[index]
                        val rawDate = when {
                            note.createdAt.isBlank() && note.updatedAt.isBlank() -> "Sin fecha"
                            note.createdAt == note.updatedAt -> note.createdAt
                            else -> note.updatedAt
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

    // Diálogo de acciones sobre una nota
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
                        text = stringResource(id = R.string.option_view_details),
                        leadingIcon = Icons.Default.Search,
                        onClick = {
                            navController.navigate("detail/${selectedNote!!.id}")
                            showOptionsDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    CustomButton(
                        text = stringResource(id = R.string.option_edit_note),
                        leadingIcon = Icons.Default.Edit,
                        onClick = {
                            navController.navigate("add_edit/${selectedNote!!.id}")
                            showOptionsDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    CustomButton(
                        text = stringResource(id = R.string.option_delete_note),
                        leadingIcon = Icons.Default.Delete,
                        onClick = {
                            viewModel.deleteNoteById(selectedNote!!.id)
                            showOptionsDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showOptionsDialog = false }) {
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

