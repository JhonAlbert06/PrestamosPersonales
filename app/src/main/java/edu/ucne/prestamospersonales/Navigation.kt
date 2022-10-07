package edu.ucne.prestamospersonales

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Screen.HomePrestamo.route"
    ){
        composable(route = "Screen.HomePrestamo.route"){
            //PrestamosScreen(navController = navController)
        }
        composable(
            route = "Screen.EditPrestamo.route",
            arguments = listOf(
                navArgument(
                    name = "ocupacionId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            //PrestamoScreen(navController = navController)
        }
    }
}