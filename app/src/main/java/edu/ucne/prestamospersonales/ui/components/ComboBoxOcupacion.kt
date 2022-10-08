package edu.ucne.prestamospersonales.ui.components

import androidx.compose.material.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.data.models.Ocupacion

@Composable
fun ComboBoxOcupacion(isError: Boolean, msg: String): Int? {

    var aux: Ocupacion = Ocupacion(0,"", 0.0)
    var ocupacionSelected by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val ocupaciones = listOf(
        Ocupacion(ocupacionId = 1, descripcion = "Ingeniero", salario = 60000.0),
        Ocupacion(ocupacionId = 2, descripcion = "Agricultor", salario = 50000.0),
        Ocupacion(ocupacionId = 3, descripcion = "Pintor", salario = 40000.0),
        Ocupacion(ocupacionId = 4, descripcion = "Maestro", salario = 60000.0),
        Ocupacion(ocupacionId = 5, descripcion = "Doctor", salario = 70000.0)
    )

    Column(Modifier.padding(10.dp)) {

        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.Ocupaciones)) },
            value = ocupacionSelected,
            onValueChange = { ocupacionSelected = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp,)
                .padding(
                    bottom = if (false){
                        0.dp
                    }else{10.dp}
                )
                .clickable { expanded = true },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            ocupaciones.forEach { ocupacion ->
                DropdownMenuItem(onClick = {
                    aux = ocupacion
                    expanded = false
                    ocupacionSelected = ocupacion.descripcion
                }
                ) {
                    Text(text = ocupacion.descripcion)
                }
            }
        }

        if (isError) {
            Text(
                text = msg,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

    }

    return aux.ocupacionId
}