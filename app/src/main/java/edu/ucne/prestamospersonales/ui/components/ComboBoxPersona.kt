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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.data.models.Persona

@Composable
fun ComboBoxPersona() : Int? {

    var aux: Persona = Persona()
    var personaSelected by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val personas = listOf(
        Persona(1,"Jhon Albert","8092907606","8092907606","gjhon7778@gmail.com","San Francisco de Macoris","19/08/2002",1,50000.0),
        Persona(2,"Alberson","8092907606","8092907606","Albert@gmail.com","San Juan","12/04/2002",1,30000.0),
        Persona(3,"Jhony Alberto","8092907606","8092907606","gjh8@gmail.com","La Vega","09/4/2004",2,40000.0),
        Persona(4,"Yecenia Altagracia","8092907606","8092907606","jh7778@gmail.com","Salida la Capital","19/03/1999",4,80000.0),
        Persona(5,"Yerlin Altagracia","8092907606","8092907606","gjon8@gmail.com","San Francisco","15/10/1945",3,90000.0)
    )

    Column(Modifier.padding(10.dp)) {

        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.Personas)) },
            value = personaSelected,
            onValueChange = { personaSelected = it },
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
            personas.forEach { persona ->
                DropdownMenuItem(onClick = {
                    aux = persona
                    expanded = false
                    personaSelected = persona.nombres
                }
                ) {
                    Text(text = persona.nombres)
                }
            }
        }

    }

    return  aux.personaId
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Previewtexbox() {
    ComboBoxPersona()
}