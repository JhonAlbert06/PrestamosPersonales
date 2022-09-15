package edu.ucne.prestamospersonales.feature_personas.presentation.components

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import edu.ucne.prestamospersonales.R
import java.util.*


@Composable
fun ElijeFecha(): String {

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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)
            .border(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
            .clickable {
                datePickerDialog.show()
            }
    ) {

        Box{
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth().padding(3.dp)
                    .wrapContentSize(Alignment.BottomEnd)
                    .size(40.dp, 50.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }

        Box{
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.FechaNacimiento),
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }



    return fecha
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Previewfecha() {
    ElijeFecha()
}