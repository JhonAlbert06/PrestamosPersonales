package edu.ucne.prestamospersonales

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.prestamospersonales.feature_Ocupaciones.presentacion.registro.OcupacionR_Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Consulta.route
    ){
        composable(route = Screen.Consulta.route){
            //OcupacionScreen(navController = navController)
        }
        composable(
            route = Screen.Registro.route,
            arguments = listOf(
                navArgument(
                    name = "OcupacionId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            OcupacionR_Screen(navController = navController)
        }
    }
}

