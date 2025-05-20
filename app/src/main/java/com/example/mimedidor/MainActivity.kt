package com.empresa.mmedidor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.empresa.mmedidor.ui.theme.MiMedidorTheme
import com.empresa.mmedidor.view.PantallaFormulario
import com.empresa.mmedidor.view.PantallaListado
import com.empresa.mmedidor.viewmodel.MedicionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiMedidorTheme {
                val navController = rememberNavController()
                val viewModel: MedicionViewModel = viewModel()

                NavHost(navController = navController, startDestination = "listado") {
                    composable("listado") {
                        PantallaListado(viewModel = viewModel, navController = navController)
                    }
                    composable("formulario") {
                        PantallaFormulario(viewModel = viewModel, navController = navController)
                    }
                }
            }
        }
    }
}
