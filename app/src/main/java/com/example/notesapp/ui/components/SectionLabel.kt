/**
 * SectionLabel.kt
 *
 * Composable reutilizable que representa una etiqueta de sección con formato personalizado.
 * Permite mostrar un texto con estilo configurable y, opcionalmente, un icono al inicio de la fila.
 *
 * Funcionalidades principales:
 * - Muestra un texto estilizado para identificar una sección de la interfaz.
 * - Permite incluir un icono opcional antes del texto.
 * - Aplica negrita al título para mejorar la jerarquía visual.
 *
 * Este componente es utilizado en varias pantallas de la aplicación NotesApp para mantener la consistencia en la presentación de títulos de sección.
 */

package com.example.notesapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SectionLabel(
    title: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null, // Icono opcional
    contentDescription: String? = null
) {
    Row(modifier = modifier) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = contentDescription
            )
            Spacer(modifier = Modifier.width(8.dp)) // Separación entre ícono y texto
        }
        Text(
            text = title,
            style = style.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}
