package edu.ucne.prestamospersonales.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.data.local.models.Prestamo

@Composable
fun ItemPrestamo(
    modifier: Modifier = Modifier,
    prestamo: Prestamo,
    onEditPrestamo: () -> Unit,
    onDeletePrestamo: () -> Unit,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {


                Text(
                    text = "${stringResource(id = R.string.FechaPrestamo)}: ${prestamo.fechaPrestamo}",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${stringResource(id = R.string.FechaVence)}:  ${prestamo.fechaVence}",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${stringResource(id = R.string.Persona)}: ${prestamo.personaId}",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(id = R.string.Concepto)}: ${prestamo.concepto}",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(id = R.string.Monto)}: ${prestamo.monto}",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(id = R.string.Balance)}: ${prestamo.balance}",
                    style = MaterialTheme.typography.h6
                )
            }

            Row {

                IconButton(onClick = onEditPrestamo) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

                IconButton(onClick = onDeletePrestamo) {
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
