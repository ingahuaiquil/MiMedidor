package com.empresa.mmedidor.view

//
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.empresa.mmedidor.viewmodel.MedicionViewModel
import com.empresa.mmedidor.R
import com.empresa.mmedidor.data.Medicion
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun PantallaListado(viewModel: MedicionViewModel, navController: NavController) {
    val mediciones: List<Medicion> by viewModel.mediciones.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("formulario") }) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.agregar))
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(mediciones) { medicion: Medicion ->
                ListItem(
                    leadingContent = {
                        Icon(
                            painter = painterResource(
                                id = when (medicion.tipo) {
                                    "agua" -> R.drawable.ic_agua
                                    "luz" -> R.drawable.ic_luz
                                    else -> R.drawable.ic_gas
                                }
                            ),
                            contentDescription = null
                        )
                    },
                    headlineContent = { Text("${medicion.tipo.replaceFirstChar { it.uppercase() }}: ${medicion.valor}") },
                    supportingContent = { Text(medicion.fecha) }
                )
            }
        }
    }
}

