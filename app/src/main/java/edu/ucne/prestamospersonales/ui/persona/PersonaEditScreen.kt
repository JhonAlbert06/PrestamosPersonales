package edu.ucne.prestamospersonales.ui.persona

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.ui.components.ComboBox
import edu.ucne.prestamospersonales.ui.components.ComboFecha
import edu.ucne.prestamospersonales.ui.components.InputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PersonaEditScreen(
    navController: NavController,
    viewModel: PersonaEditViewModel = hiltViewModel(),
){

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when (event){
                is PersonaEditViewModel.UiEvent.SavePersona -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(

        topBar = {
            PersonaEditTopBar(
                topAppBarText = stringResource(id = R.string.addPersona)
            )
        },

        content = {
            viewModel.isError = Validacion(
                viewModel = viewModel,
                onEvent = { viewModel.onEvent(it) }
            )
        },

        bottomBar = {
            PersonaEditBottomBar(
                onInsertPersona = { viewModel.onEvent(PersonaEditEvent.InsertPersona)},
                isError = viewModel.isError
            )
        }

    )
}

@Composable
fun PersonaEditConten(
    viewModel: PersonaEditViewModel,
    IsErrorNombres: Boolean,
    IsErrorTelefono: Boolean,
    IsErrorCelular: Boolean,
    IsErrorEmail: Boolean,
    IsErrorDireccion: Boolean,
    IsErrorFechaNacimiento: Boolean,
    IsErrorOcupacionId: Boolean,
    IsErrorBalance: Boolean,
    msgNombres: String,
    msgTelefono: String,
    msgCelular: String,
    msgEmail: String,
    msgDireccion: String,
    msgFechaNacimiento: String,
    msgOcupacionId: String,
    msgBalance: String,
    onEvent: (PersonaEditEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        InputText(
            isError = IsErrorNombres,
            msgError = msgNombres,
            text = viewModel.nombres.value.text,
            label = stringResource(id = R.string.Nombres),
            onTextChange = {onEvent(PersonaEditEvent.EnteredNombres(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )

        InputText(
            isError = IsErrorTelefono,
            msgError = msgTelefono,
            text = viewModel.telefono.value.text,
            label = stringResource(id = R.string.Telefono),
            onTextChange = {onEvent(PersonaEditEvent.EnteredTelefono(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )

        InputText(
            isError = IsErrorCelular,
            msgError = msgCelular,
            text = viewModel.celular.value.text,
            label = stringResource(id = R.string.Celular),
            onTextChange = {onEvent(PersonaEditEvent.EnteredCelular(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )

        InputText(
            isError = IsErrorEmail,
            msgError = msgEmail,
            text = viewModel.email.value.text,
            label = stringResource(id = R.string.Email),
            onTextChange = {onEvent(PersonaEditEvent.EnteredEmail(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )

        InputText(
            isError = IsErrorDireccion,
            msgError = msgDireccion,
            text = viewModel.direccion.value.text,
            label = stringResource(id = R.string.Direccion),
            onTextChange = {onEvent(PersonaEditEvent.EnteredDireccion(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )

        viewModel.fechaNacimiento.value.text = ComboFecha()
        viewModel.ocupacionId.value.text = ComboBox().toString()

        InputText(
            isError = IsErrorBalance,
            msgError = msgBalance,
            text = viewModel.balance.value.text,
            label = stringResource(id = R.string.Balance),
            onTextChange = {onEvent(PersonaEditEvent.EnteredBalance(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            readOnly = true
        )
    }
}

@Composable
fun PersonaEditBottomBar(
    modifier: Modifier = Modifier,
    onInsertPersona: () -> Unit,
    isError: Boolean
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 18.dp),
        shape = CircleShape,
        border= BorderStroke(1.dp, Color.Green),
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
fun PersonaEditTopBar(topAppBarText: String) {
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

fun isNumeric(aux: String): Boolean{
    return try{
        aux.toDouble()
        true
    }catch(e: NumberFormatException){
        false
    }
}


@Composable
fun Validacion(
    viewModel: PersonaEditViewModel,
    onEvent: (PersonaEditEvent) -> Unit
) : Boolean {

    var isErrorNombres = false
    var isErrorTelefono = false
    var isErrorCelular = false
    var isErrorEmail = false
    var isErrorDireccion = false

    var mgsNombres = ""
    var mgsTelefono = ""
    var mgsCelular = ""
    var mgsEmail = ""
    var mgsDireccion = ""

    if (viewModel.nombres.value.text.isEmpty()) {
        isErrorNombres = true
        mgsNombres = "*Campo Obligatorio*"
    } else if (viewModel.nombres.value.text.isDigitsOnly()) {
        isErrorNombres = true
        mgsNombres = "Solo se permiten Caracteres"
    } else if (!(viewModel.nombres.value.text.any { it.isLetter() })) {
        isErrorNombres = true
        mgsNombres = "No se permiten simbolos"
    }

    if (viewModel.telefono.value.text.isEmpty()) {
        isErrorTelefono = true
        mgsTelefono = "*Campo Obligatorio*"
    } else if (viewModel.telefono.value.text.length < 10 && viewModel.telefono.value.text.isNotEmpty()) {
        isErrorTelefono = true
        mgsTelefono = "Minimo 10 Numeros"
    } else if (!isNumeric(viewModel.telefono.value.text)) {
        isErrorTelefono = true
        mgsTelefono = "No es una Numero"
    }

    if (viewModel.celular.value.text.isEmpty()) {
        isErrorCelular = true
        mgsCelular = "*Campo Obligatorio*"
    } else if (viewModel.celular.value.text.length < 10 && viewModel.celular.value.text.isNotEmpty()) {
        isErrorCelular = true
        mgsCelular = "Minimo (10) Numeros"
    } else if (!isNumeric(viewModel.celular.value.text)) {
        isErrorCelular = true
        mgsCelular = "No es una Numero"
    }

    if (viewModel.email.value.text.isBlank()) {
        isErrorEmail = true
        mgsEmail = "*Campo Obligatorio*"
    } else if (
        !Patterns.EMAIL_ADDRESS
            .matcher(viewModel.email.value.text)
            .matches()
    ) {
        isErrorEmail = true
        mgsEmail = "El Email no es valido"
    }

    if (viewModel.direccion.value.text.length < 5 && viewModel.direccion.value.text.isNotEmpty()) {
        isErrorDireccion = true
        mgsDireccion = "Caracteres insuficientes Mínimo, (5)";
    } else if (viewModel.direccion.value.text.isEmpty()) {
        isErrorDireccion = true
        mgsDireccion = "*Campo Obligatorio*";
    } else if (!(viewModel.direccion.value.text.any { it.isLetter() })) {
        isErrorDireccion = true
        mgsDireccion = "Direccion no valida";
    }


    PersonaEditConten(
        viewModel = viewModel,
        IsErrorNombres = isErrorNombres,
        IsErrorTelefono = isErrorTelefono,
        IsErrorCelular = isErrorCelular,
        IsErrorEmail = isErrorEmail,
        IsErrorDireccion = isErrorDireccion,
        IsErrorFechaNacimiento = false,
        IsErrorOcupacionId = false,
        IsErrorBalance = false,
        msgNombres = mgsNombres,
        msgTelefono = mgsTelefono,
        msgCelular = mgsCelular,
        msgEmail = mgsEmail,
        msgDireccion = mgsDireccion,
        msgFechaNacimiento = "",
        msgOcupacionId = "",
        msgBalance = "",
        onEvent = { onEvent(it) }
    )

    return isErrorNombres || isErrorTelefono || isErrorCelular || isErrorEmail || isErrorDireccion
}