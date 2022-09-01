package edu.ucne.prestamospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.ui.theme.PrestamosPersonalesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrestamosPersonalesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CuadroCompose(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun CuadroCompose(modifier: Modifier)
{
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {

        Text(text = "Prestamos Personales")

        CuadroTexto(
            descripcion = "Descripción",
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onNext = {focusManager.clearFocus()})
        )

        CuadroTexto(
            descripcion = "Salario",
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}))

        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth().padding(18.dp),
            shape = CircleShape,
            border= BorderStroke(1.dp, Color.Blue)

        ) {
            Icon(Icons.Default.Add, contentDescription = "content description")
            Text(text = "Guardar")
        }
    }
}

@Composable
fun CuadroTexto(
    descripcion : String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = descripcion) },
        value = "", 
        onValueChange = {},
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    PrestamosPersonalesTheme {
        CuadroCompose(modifier = Modifier.fillMaxSize())
    }
}