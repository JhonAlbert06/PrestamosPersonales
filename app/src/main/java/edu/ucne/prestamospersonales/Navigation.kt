package edu.ucne.prestamospersonales

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.prestamospersonales.presentation.registros.editOcupacion.OcupacionScreen
import edu.ucne.prestamospersonales.presentation.registros.editPersona.PersonaScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.EditPersona.route
    ){
        composable(route = Screen.EditPersona.route){
            PersonaScreen(navController = navController)
        }
    }
}