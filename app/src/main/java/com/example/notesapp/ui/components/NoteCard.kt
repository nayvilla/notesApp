/**
 * NoteCard.kt
 *
 * Composable que representa una tarjeta visual para mostrar una nota individual en la aplicación NotesApp.
 * Incluye título, contenido breve, fecha de creación o actualización, y un indicador visual del color asociado a la nota.
 *
 * Funcionalidades principales:
 * - Muestra el título y el contenido de la nota de forma resumida.
 * - Presenta la fecha acompañada de un icono de calendario.
 * - Integra un pequeño círculo que indica el color personalizado de la nota.
 * - Permite interacción táctil, ejecutando una acción al hacer clic (navegación o acciones relacionadas).
 * - Aplica estilo con bordes redondeados, elevación sutil y fondo coloreado según el valor de la nota.
 *
 * Este componente es utilizado dentro de listas o grillas para la visualización principal de las notas.
 */

package com.example.notesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteCard(
    title: String,
    content: String,
    date: String,
    color: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(color)) //
            .clickable { onClick() }
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 7
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Fecha con ícono
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Fecha",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                // Círculo de color de la nota
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(Color(color))
                )
            }
        }
    }
}
