/**
 * NoteSection.kt
 *
 * Composable reutilizable que proporciona un contenedor estilizado para agrupar elementos relacionados en la interfaz de usuario.
 * Envuelve su contenido dentro de un Surface con esquinas redondeadas, sombra sutil y separación vertical entre elementos.
 *
 * Funcionalidades principales:
 * - Aplica un fondo basado en el esquema de colores actual del tema.
 * - Agrega elevación tonal y sombra para resaltar visualmente la sección.
 * - Proporciona un espacio interno (padding) y separación entre elementos internos.
 *
 * Este componente es utilizado en varias pantallas de la aplicación NotesApp para mantener la organización y estética de los bloques de contenido.
 */

package com.example.notesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun NoteSection(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = content
        )
    }
}
