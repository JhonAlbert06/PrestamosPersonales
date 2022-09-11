package edu.ucne.prestamospersonales.feature_personas.presentation.components

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.R
import java.util.*


@Composable
fun ElijeFecha() : String {

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

    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { datePickerDialog.show() },
        border = BorderStroke(2.dp, Color.Gray),) {
        Text(text = stringResource(id = R.string.FechaNacimiento))
    }

    Text(text = fecha)
    return  fecha
}