package edu.ucne.prestamospersonales.presentation.registros.editPersona


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
import edu.ucne.prestamospersonales.presentation.registros.editPersona.components.ElijeFecha
import edu.ucne.prestamospersonales.presentation.registros.editPersona.components.InputText
import edu.ucne.prestamospersonales.presentation.registros.editPersona.components.TextBox
import java.lang.NumberFormatException

@Composable
fun PersonaScreen(
    navController: NavController,
    viewModel: EditPersonaViewModel = hiltViewModel()
) {
    Scaffold(

        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.addPersona)
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
        Text(text = stringResource(id = R.string.addPersona))
    }
}

@Composable
fun EditConten(
    viewModel: EditPersonaViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        InputText(
            isError = viewModel.isErrorNombres,
            errorMsg = viewModel.errorMgsNombres,
            text = viewModel.nombres,
            hint = stringResource(id = R.string.Nombres),
            onTextChange = { viewModel.nombres = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = viewModel.isErrorTelefono,
            errorMsg = viewModel.errorMgsTelefono,
            text = viewModel.telefono,
            hint = stringResource(id = R.string.Telefono),
            onTextChange = { viewModel.telefono = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = viewModel.isErrorCelular,
            errorMsg = viewModel.errorMgsCelular,
            text = viewModel.celular,
            hint = stringResource(id = R.string.Celular),
            onTextChange = { viewModel.celular = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = viewModel.isErrorEmail,
            errorMsg = viewModel.errorMgsEmail,
            text = viewModel.email,
            hint = stringResource(id = R.string.Email),
            onTextChange = { viewModel.email = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = viewModel.isErrorDireccion,
            errorMsg = viewModel.errorMgsDireccion,
            text = viewModel.direccion,
            hint = stringResource(id = R.string.Direccion),
            onTextChange = { viewModel.direccion = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {

            viewModel.fechaNacimiento = ElijeFecha()

            viewModel.ocupacionId = TextBox().toString()
        }

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


fun isNumeric(aux: String): Boolean {
    return try {
        aux.toDouble()
        true
    } catch (e: NumberFormatException) {
        false
    }
}


@Composable
fun validacion(viewModel: EditPersonaViewModel): Boolean {

    if (viewModel.nombres.isEmpty()) {
        viewModel.isErrorNombres = true
        viewModel.errorMgsNombres = "*Campo Obligatorio*"
    } else if (viewModel.nombres.isDigitsOnly()) {
        viewModel.isErrorNombres = true
        viewModel.errorMgsNombres = "Solo se permiten Caracteres"
    } else if (!(viewModel.nombres.any { it.isLetter() })) {
        viewModel.isErrorNombres = true
        viewModel.errorMgsNombres = "No se permiten simbolos"
    }

    if (viewModel.telefono.isEmpty()) {
        viewModel.isErrorTelefono = true
        viewModel.errorMgsTelefono = "*Campo Obligatorio*"
    } else if (viewModel.telefono.length < 10 && viewModel.telefono.isNotEmpty()) {
        viewModel.isErrorTelefono = true
        viewModel.errorMgsTelefono = "Minimo 10 Numeros"
    } else if (!isNumeric(viewModel.telefono)) {
        viewModel.isErrorTelefono = true
        viewModel.errorMgsTelefono = "No es una Numero"
    }

    if (viewModel.celular.isEmpty()) {
        viewModel.isErrorCelular = true
        viewModel.errorMgsCelular = "*Campo Obligatorio*"
    } else if (viewModel.celular.length < 10 && viewModel.celular.isNotEmpty()) {
        viewModel.isErrorCelular = true
        viewModel.errorMgsCelular = "Minimo (10) Numeros"
    } else if (!isNumeric(viewModel.celular)) {
        viewModel.isErrorCelular = true
        viewModel.errorMgsCelular = "No es una Numero"
    }

    if (viewModel.email.isBlank()) {
        viewModel.isErrorEmail = true
        viewModel.errorMgsEmail = "*Campo Obligatorio*"
    } else if (
        !Patterns.EMAIL_ADDRESS
            .matcher(viewModel.email)
            .matches()
    ) {
        viewModel.isErrorEmail = true
        viewModel.errorMgsEmail = "El Email no es valido"
    }

    if (viewModel.direccion.length < 5 && viewModel.direccion.isNotEmpty()) {
        viewModel.isErrorDireccion = true
        viewModel.errorMgsDireccion = "Caracteres insuficientes Mínimo, (5)";
    } else if (viewModel.direccion.isEmpty()) {
        viewModel.isErrorDireccion = true
        viewModel.errorMgsDireccion = "*Campo Obligatorio*";
    } else if (!(viewModel.direccion.any { it.isLetter() })) {
        viewModel.isErrorDireccion = true
        viewModel.errorMgsDireccion = "Direccion no valida";
    }

    EditConten(viewModel = viewModel)

    return (viewModel.isErrorNombres && viewModel.isErrorTelefono && viewModel.isErrorCelular && viewModel.isErrorEmail && viewModel.isErrorDireccion)
}
