package edu.ucne.prestamospersonales

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.prestamospersonales.feature_personas.presentation.editPersona.EditScreen
import edu.ucne.prestamospersonales.feature_personas.presentation.homePersona.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomePersona.route
    ){
        composable(route = Screen.HomePersona.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.EditPersona.route,
            arguments = listOf(
                navArgument(
                    name = "personaId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            EditScreen(navController = navController)
        }
    }
}