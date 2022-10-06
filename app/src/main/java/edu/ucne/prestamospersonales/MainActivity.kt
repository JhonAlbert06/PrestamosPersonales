package edu.ucne.prestamospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
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
