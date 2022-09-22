package edu.ucne.prestamospersonales

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.prestamospersonales.presentation.consulta.homePrestamo.PrestamosList
import edu.ucne.prestamospersonales.presentation.registros.editPrestamo.PrestamoScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomePrestamo.route
    ){
        composable(route = Screen.HomePrestamo.route){
            PrestamosList(navController = navController)
        }
        composable(
            route = Screen.EditPrestamo.route,
            arguments = listOf(
                navArgument(
                    name = "ocupacionId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            //PrestamoScreen(navController = navController, usuario = usuario)
        }
    }
}