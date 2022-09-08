package edu.ucne.prestamospersonales.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.prestamospersonales.presentation.edit.Validacion
import edu.ucne.prestamospersonales.ui.theme.PrestamosPersonalesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrestamosPersonalesTheme {
                Navigation()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevieeScreen(){
    PrestamosPersonalesTheme {
        Navigation()
    }
}