package edu.ucne.prestamospersonales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    ScreenOcupaciones()
                }
            }
        }
    }
}

@Composable
fun ScreenOcupaciones()
{
    var descripcion by remember { mutableStateOf("")}
    var salario by remember { mutableStateOf("")}

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {

        Text(
            text = "Registro de Ocupaciones",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        CuadroTexto(
            value = descripcion,
            onValueChange = {descripcion = it},
            descripcion = "Descripción",
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions =
            KeyboardActions(onNext = {
                focusManager.clearFocus()
            })
        )

        CuadroTexto(
            value = salario,
            onValueChange = {salario = it},
            descripcion = "Salario",
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions =
            KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )

        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth().padding(18.dp),
            shape = CircleShape,
            border= BorderStroke(1.dp, Color.Gray)

        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Icono de Add"
            )
            Text(
                text = "Guardar",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun CuadroTexto(
    value: String,
    onValueChange: (String)->Unit,
    descripcion : String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        label = { Text(text = descripcion) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    PrestamosPersonalesTheme {
        ScreenOcupaciones()
    }
}