package edu.ucne.prestamospersonales.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.data.local.models.Ocupacion
import java.text.DecimalFormat


@Composable
fun Item(
    modifier: Modifier = Modifier,
    ocupacion: Ocupacion,
    onEditOcupacion: () -> Unit,
    onDeleteOcupacion: () -> Unit
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
                    text = ocupacion.descripcion,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(4.dp))

                val decimal = DecimalFormat("#,###.######")
                Text(
                    text =  "$ ${decimal.format(ocupacion.salario)}",
                    style = MaterialTheme.typography.h6
                )
            }

            Row {

                IconButton(onClick = onEditOcupacion ){
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

                IconButton(onClick = onDeleteOcupacion) {
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