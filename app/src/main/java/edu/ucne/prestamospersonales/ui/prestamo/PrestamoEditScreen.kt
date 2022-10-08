package edu.ucne.prestamospersonales.ui.prestamo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.ui.components.ComboBoxPersona
import edu.ucne.prestamospersonales.ui.components.ComboFecha
import edu.ucne.prestamospersonales.ui.components.InputText
import edu.ucne.prestamospersonales.ui.persona.PersonaEditEvent

@Composable
fun PrestamoEditScreen(
    navController: NavController,
    viewModel: PrestamoEditViewModel = hiltViewModel()
) {

    Scaffold(

        topBar = {
            PrestamoEditTopBar(
                topAppBarText = stringResource(id = R.string.addPrestamo)
            )
        },

        content = {
            viewModel.isError = validacion(
                viewModel = viewModel,
                onEvent = { viewModel.onEvent(it) }
            )
        },

        bottomBar = {
            PrestamoEditBottomBar(
                onInsertPrestamo = { viewModel.onEvent(PrestamoEditEvent.InsertPrestamo) },
                isError = viewModel.isError
            )
        }

    )
}

@Composable
fun PrestamoEditBottomBar(
    modifier: Modifier = Modifier,
    onInsertPrestamo: () -> Unit,
    isError: Boolean
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 18.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Green),
        onClick = { onInsertPrestamo() },
        enabled = isError

    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null
        )
        Text(text = stringResource(id = R.string.addPrestamo))
    }
}

@Composable
fun PrestamoEditConten(
    viewModel: PrestamoEditViewModel,
    onEvent: (PrestamoEditEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        viewModel.fechaPrestamo.value.text = ComboFecha()
        viewModel.fechaVence.value.text = ComboFecha()
        viewModel.personaId.value.text = ComboBoxPersona().toString()

        InputText(
            isError = true,
            msgError = "*Campo Obligatorio*",
            text = viewModel.concepto.value.text,
            label = stringResource(id = R.string.Concepto),
            onTextChange = {onEvent(PrestamoEditEvent.EnteredConcepto(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = true,
            msgError = "*Campo Obligatorio*",
            text = viewModel.monto.value.text,
            label = stringResource(id = R.string.Monto),
            onTextChange = { onEvent(PrestamoEditEvent.EnteredMonto(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = true,
            msgError = "*Campo Obligatorio*",
            text = viewModel.balance.value.text,
            label = stringResource(id = R.string.Balance),
            onTextChange = { onEvent(PrestamoEditEvent.EnteredBalance(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )


    }
}

@Composable
fun PrestamoEditTopBar(topAppBarText: String) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun validacion(
    viewModel: PrestamoEditViewModel,
    onEvent: (PrestamoEditEvent) -> Unit
): Boolean {
    PrestamoEditConten(viewModel = viewModel, onEvent = onEvent)
    return (true)
}