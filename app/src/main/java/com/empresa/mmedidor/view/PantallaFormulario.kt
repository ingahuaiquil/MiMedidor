package com.empresa.mmedidor.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.empresa.mmedidor.viewmodel.MedicionViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PantallaFormulario(viewModel: MedicionViewModel, navController: NavController) {
    var tipo by remember { mutableStateOf("agua") }
    var valor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    var errorValor by remember { mutableStateOf<String?>(null) }
    var errorFecha by remember { mutableStateOf<String?>(null) }

    val opciones = listOf("agua", "luz", "gas")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Registro Medidor", style = MaterialTheme.typography.headlineSmall)

        // Radio Buttons
        Text("Medidor de:")
        opciones.forEach { opcion ->
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                RadioButton(
                    selected = tipo == opcion,
                    onClick = { tipo = opcion }
                )
                Text(text = opcion.replaceFirstChar { it.uppercase() })
            }
        }

        // Campo Valor
        OutlinedTextField(
            value = valor,
            onValueChange = {
                valor = it
                errorValor = null
            },
            label = { Text("Valor del medidor") },
            isError = errorValor != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorValor != null) {
            Text(text = errorValor ?: "", color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        // Campo Fecha
        OutlinedTextField(
            value = fecha,
            onValueChange = {
                fecha = it
                errorFecha = null
            },
            label = { Text("Fecha (DD-MM-YYYY)") },
            isError = errorFecha != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (errorFecha != null) {
            Text(text = errorFecha ?: "", color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        // Botón Guardar
        Button(
            onClick = {
                val valorFloat = valor.toFloatOrNull()
                val fechaValida = validarFecha(fecha)

                if (valorFloat == null) {
                    errorValor = "El valor debe ser numérico."
                }

                if (!fechaValida) {
                    errorFecha = "La fecha debe tener el formato DD-MM-YYYY."
                }

                if (valorFloat != null && fechaValida) {
                    viewModel.agregarMedicion(tipo, valorFloat, fecha)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar medición")
        }
    }
}

// Función para validar fecha en formato YYYY-MM-DD
fun validarFecha(fecha: String): Boolean {
    return try {
        val formato = SimpleDateFormat("dd-MM-yyyyy", Locale.getDefault())
        formato.isLenient = false
        formato.parse(fecha)
        true
    } catch (e: Exception) {
        false
    }
}
