package edu.ucne.prestamospersonales.presentation.registros.editPrestamo

import android.util.Patterns
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
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.presentation.registros.editPersona.EditBottomBar
import edu.ucne.prestamospersonales.presentation.registros.editPersona.EditPersonaViewModel
import edu.ucne.prestamospersonales.presentation.registros.editPersona.EditTopBar
import edu.ucne.prestamospersonales.presentation.registros.editPersona.components.ElijeFecha
import edu.ucne.prestamospersonales.presentation.registros.editPersona.components.InputText
import edu.ucne.prestamospersonales.presentation.registros.editPersona.components.TextBox
import edu.ucne.prestamospersonales.presentation.registros.editPersona.isNumeric

@Composable
fun PrestamoScreen(
    navController: NavController,
    viewModel: EditPrestamoViewModel = hiltViewModel()
) {

    Scaffold(

        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.addPrestamo)
            )
        },

        content = {
            viewModel.isError = validacion(viewModel)
        },

        bottomBar = {
            EditBottomBar(
                onInsertPersona = { viewModel.save() },
                isError = viewModel.isError
            )
        }

    )
}

@Composable
fun EditBottomBar(
    modifier: Modifier = Modifier,
    onInsertPersona: () -> Unit,
    isError: Boolean
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 18.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Green),
        onClick = { onInsertPersona() },
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
fun EditConten(
    viewModel: EditPrestamoViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {

            viewModel.fechaPrestamo = ElijeFecha(
                stringResource(id = R.string.FechaPrestamo),
                true,
                ""
            )

            viewModel.fechaVence = ElijeFecha(
                stringResource(id = R.string.FechaVence),
                true,
                "")

            viewModel.personaId = TextBox().toString()
        }

        InputText(
            isError = true,
            errorMsg = "",
            text = viewModel.concepto,
            hint = stringResource(id = R.string.Concepto),
            onTextChange = { viewModel.concepto = it },
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
            errorMsg = "",
            text = viewModel.monto,
            hint = stringResource(id = R.string.Monto),
            onTextChange = { viewModel.monto = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = true,
            errorMsg = "",
            text = viewModel.balance,
            hint = stringResource(id = R.string.Balance),
            onTextChange = { viewModel.balance = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )


    }
}

@Composable
fun EditTopBar(topAppBarText: String) {
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
fun validacion(viewModel: EditPrestamoViewModel): Boolean {


    EditConten(viewModel = viewModel)

    return (true)
}

