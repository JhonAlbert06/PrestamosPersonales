package edu.ucne.prestamospersonales.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.data.models.Persona


@Composable
fun ItemPersona(
    modifier: Modifier = Modifier,
    persona: Persona,
    onEditPersona: () -> Unit,
    onDeletePersona: () -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {


                Text(
                    text = persona.nombres,
                    modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Telefono: ${persona.telefono} ",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Celular: ${persona.celular}",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Email: ${persona.email}",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "Direccion: ${persona.direccion}",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fecha de Nacimieno: ${persona.fechaNacimiento}",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ocupacion: ${persona.ocupacionId}" ,
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Balance: ${persona.balance}",
                    style = MaterialTheme.typography.h6
                )

            }

            Row {

                IconButton(onClick = onEditPersona ){
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

                IconButton(onClick = onDeletePersona) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

            }

        }
    }
}