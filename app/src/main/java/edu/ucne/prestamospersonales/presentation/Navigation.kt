package edu.ucne.prestamospersonales.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.prestamospersonales.presentation.edit.EditScreen
import edu.ucne.prestamospersonales.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Edit.route,
            arguments = listOf(
                navArgument(
                    name = "OcupacionId"
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