package edu.ucne.prestamospersonales

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.prestamospersonales.ui.ocupaciones.OcupacionEditScreen
import edu.ucne.prestamospersonales.ui.ocupaciones.OcupacionHomeScreen
import edu.ucne.prestamospersonales.ui.persona.PersonaEditScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeOcupacion.route
    ) {

        composable(route = Screen.HomeOcupacion.route) {
            OcupacionHomeScreen(navController = navController)
        }

        composable(
            route = Screen.EditOcupacion.route,
            arguments = listOf(
                navArgument(
                    name = "ocupacionId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            OcupacionEditScreen(navController = navController)
        }

        composable(
            route = Screen.EditPersona.route,
            arguments = listOf(
                navArgument(
                    name = "personaId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            PersonaEditScreen(navController = navController)
        }

    }
}