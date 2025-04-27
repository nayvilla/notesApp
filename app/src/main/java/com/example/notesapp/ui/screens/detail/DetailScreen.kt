package com.example.notesapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notesapp.data.local.NoteDatabaseProvider
import com.example.notesapp.ui.screens.detail.DetailViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    noteId: Int
) {
    val context = LocalContext.current
    val repository = NoteDatabaseProvider.getRepository(context)
    val viewModel: DetailViewModel = viewModel(
        factory = DetailViewModelFactory(repository, noteId)
    )

    val note by viewModel.note.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Nota", style = MaterialTheme.typography.titleLarge) },
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
                .background(Color(note.color))
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Título:",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Contenido:",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Color seleccionado:",
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = "Código: ${note.color}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Nombre: ${getColorNameFromInt(note.color)}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Fecha de creación:",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = note.createdAt,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Última actualización:",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = note.updatedAt,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

fun getColorNameFromInt(colorInt: Int): String {
    return when (colorInt) {
        Color.Yellow.value.toInt() -> "Amarillo"
        Color.Cyan.value.toInt() -> "Cian"
        Color.Green.value.toInt() -> "Verde"
        Color.Red.value.toInt() -> "Rojo"
        Color.Blue.value.toInt() -> "Azul"
        Color.LightGray.value.toInt() -> "Gris Claro"
        else -> "Personalizado"
    }
}
