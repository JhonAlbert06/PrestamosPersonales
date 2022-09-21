package edu.ucne.prestamospersonales

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.prestamospersonales.presentation.registros.editOcupacion.OcupacionScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.EditOcupacion.route
    ){
        composable(route = Screen.EditOcupacion.route){
            OcupacionScreen(navController = navController)
        }
    }
}