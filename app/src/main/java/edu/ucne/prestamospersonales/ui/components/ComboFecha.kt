package edu.ucne.prestamospersonales.ui.components

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.R
import java.util.*


@Composable
fun ComboFecha(isError: Boolean, mgs: String): String {

    val calendario = Calendar.getInstance()
    val Año = calendario.get(Calendar.YEAR)
    val Mes = calendario.get(Calendar.MONTH)
    val Dia = calendario.get(Calendar.DAY_OF_MONTH)

    val contexto = LocalContext.current

    var fecha by remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(
        contexto, { d, Año, Mes, Dia ->
            val mes = Mes + 1
            fecha = "$Dia/$mes/$Año"
        }, Año, Mes, Dia
    )

    OutlinedTextField(
        label = { Text(text = stringResource(id = R.string.FechaNacimiento)) },
        value = fecha,
        onValueChange = { fecha = it },
        enabled = false,
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .padding(
                bottom = if (false){
                    0.dp
                }else{10.dp}
            )
            .clickable { datePickerDialog.show() },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface
            )
        }
    )

    if (isError) {
        Text(
            text = mgs,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }

    return fecha
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Previewfecha() {
    //ComboFecha(isErrorFechaPrestamo, mgsFechaPrestamo)
}