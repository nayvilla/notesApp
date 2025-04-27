/**
 * CustomButton.kt
 *
 * Composable que define un botón personalizado utilizado de forma reutilizable en la aplicación NotesApp.
 * Integra diseño visual consistente con el tema principal, iconografía opcional y configuraciones de elevación y estilo.
 *
 * Funcionalidades principales:
 * - Permite mostrar un botón con texto centrado y un icono opcional al inicio.
 * - Aplica esquinas redondeadas utilizando la configuración `shapes.large` del tema.
 * - Configura colores de fondo y contenido basados en el esquema de colores de MaterialTheme.
 * - Define elevaciones personalizadas para el estado normal y presionado.
 *
 * Este componente es utilizado en diferentes pantallas para acciones como ver detalles, editar o eliminar notas.
 */

package com.example.notesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null // Icono opcional
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.large, // Redondeado elegante
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 10.dp
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
