package edu.ucne.prestamospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.prestamospersonales.presentation.registros.editOcupacion.OcupacionScreen
import edu.ucne.prestamospersonales.presentation.registros.editPrestamo.PrestamoScreen
import edu.ucne.prestamospersonales.ui.theme.PrestamosPersonalesTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrestamosPersonalesTheme {
                //Navigation()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "LoginScreen"
                ) {

                    composable("LoginScreen") {
                        LoginScreen(navController)
                    }

                    composable("OcupacionScreen") {
                        OcupacionScreen(navController = navController)
                    }

                    composable("PrestamoScreen/{usuario}") { navEntry ->
                        val usuario = navEntry.arguments?.getString("usuario") ?: ""
                        PrestamoScreen(navController = navController, usuario = usuario)
                    }

                    /*composable("PrestamoScreen?carrera={carrera}&edad={edad}") { navEntry ->
                        val carrera =
                        val usuario = navEntry.arguments?.getString("usuario") ?: ""
                        PrestamoScreen(navController = navController, usuario = usuario)
                    }*/
                }

            }
        }
    }
}
//{}

@Composable
fun LoginScreen(navigation: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        var usuario by rememberSaveable {
            mutableStateOf("")
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(0.dp, Color.Black),
            value = usuario,
            onValueChange = { usuario = it }
        )
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { navigation.navigate("PrestamoScreen/$usuario") }
        ) {
            Text(text = "Click")
        }
    }

}


