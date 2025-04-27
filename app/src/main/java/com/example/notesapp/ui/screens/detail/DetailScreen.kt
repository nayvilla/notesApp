/**
 * DetailScreen.kt
 *
 * Esta clase define la pantalla de detalles de una nota dentro de la aplicación.
 * Permite visualizar de manera organizada la información completa de una nota previamente creada,
 * mostrando campos como título, contenido, color, fecha de creación y fecha de actualización.
 *
 * Funcionalidades principales:
 * - Visualizar los detalles de una nota seleccionada.
 * - Proporcionar un botón para navegar a la edición de la nota.
 * - Mantener el diseño adaptado a Material Design 3 usando Jetpack Compose.
 * - Aplicar desplazamiento vertical para el contenido extenso.
 *
 * La pantalla forma parte de la arquitectura MVVM utilizando un ViewModel dedicado para la obtención de datos.
 */

package com.example.notesapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notesapp.data.local.NoteDatabaseProvider
import com.example.notesapp.ui.components.SectionLabel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import com.example.notesapp.ui.components.CustomButton
import com.example.notesapp.ui.components.NoteSection
import androidx.compose.ui.res.stringResource
import com.example.notesapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    noteId: Int
) {
    val context = LocalContext.current
    val repository = NoteDatabaseProvider.getRepository(context)
    val viewModel: DetailViewModel = viewModel(factory = DetailViewModelFactory(repository, noteId))

    val note by viewModel.note.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_detail_screen), style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Título
                    NoteSection {
                        SectionLabel(
                            title = "Título",
                            style = MaterialTheme.typography.bodyLarge,
                            leadingIcon = Icons.Default.Title
                        )
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    // Color Seleccionado
                    NoteSection {
                        SectionLabel(
                            title = "Color Seleccionado",
                            style = MaterialTheme.typography.bodyLarge,
                            leadingIcon = Icons.Default.Palette
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(width = 120.dp, height = 50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(note.color))
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    // Contenido
                    NoteSection {
                        SectionLabel(
                            title = "Contenido",
                            style = MaterialTheme.typography.bodyLarge,
                            leadingIcon = Icons.Default.Description
                        )
                        Text(
                            text = note.content,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 6,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    // Fecha de Creación
                    NoteSection {
                        SectionLabel(
                            title = "Fecha de Creación",
                            style = MaterialTheme.typography.bodyLarge,
                            leadingIcon = Icons.Default.CalendarToday
                        )
                        Text(
                            text = note.createdAt,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    // Última Actualización
                    NoteSection {
                        SectionLabel(
                            title = "Última Actualización",
                            style = MaterialTheme.typography.bodyLarge,
                            leadingIcon = Icons.Default.Update
                        )
                        Text(
                            text = note.updatedAt,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    CustomButton(
                        text = "Editar Nota",
                        leadingIcon = Icons.Default.Edit,
                        onClick = {
                            navController.navigate("add_edit/${note.id}") // Redireccionar a editar nota
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )
                }
            }
        }
    }
}


