package com.example.notesapp.ui.screens.add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notesapp.data.local.NoteDatabaseProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val repository = NoteDatabaseProvider.getRepository(context)
    val viewModel: AddEditViewModel = viewModel(
        factory = AddEditViewModelFactory(repository)
    )

    val title by viewModel.title.collectAsState()
    val content by viewModel.content.collectAsState()
    val colorInt by viewModel.color.collectAsState()
    val updatedAt by viewModel.updatedAt.collectAsState()
    //val isEditing by viewModel.isEditing.collectAsState() //

    val colorSelected = Color(colorInt)
    var showColorPicker by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = colorSelected,
        topBar = {
            TopAppBar(
                title = {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        tonalElevation = 2.dp
                    ) {
                        Text(
                            text = if (true) "Actualizado el: $updatedAt" else "Creado el: $updatedAt",
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retroceder")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.saveNote()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Save, contentDescription = "Guardar Nota")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { viewModel.updateTitle(it) },
                placeholder = { Text("Título", style = MaterialTheme.typography.titleLarge) },
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { viewModel.updateContent(it) },
                placeholder = { Text("Escribe tu nota aquí...", style = MaterialTheme.typography.bodyLarge) },
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )
        }
    }

    if (showColorPicker) {
        AlertDialog(
            onDismissRequest = { showColorPicker = false },
            confirmButton = {
                Button(
                    onClick = { showColorPicker = false }
                ) { Text("Cerrar") }
            },
            title = { Text("Seleccionar Color") },
            text = {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    listOf(
                        Color(0xFFFFFF00), // Amarillo
                        Color(0xFF00FFFF), // Cyan
                        Color(0xFF00FF00), // Verde
                        Color(0xFFFF0000), // Rojo
                        Color(0xFF0000FF)  // Azul
                    ).forEach { colorOption ->
                        Surface(
                            color = colorOption,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    viewModel.updateColor(colorOption)
                                    showColorPicker = false
                                }
                        ) {}
                    }
                }
            }
        )
    }
}
