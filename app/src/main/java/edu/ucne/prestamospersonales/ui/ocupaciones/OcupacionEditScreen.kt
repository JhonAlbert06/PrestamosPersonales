package edu.ucne.prestamospersonales.ui.ocupaciones

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.ui.components.InputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OcupacionEditScreen(
    navController: NavController,
    viewModel: OcupacionEditViewModel = hiltViewModel(),
){
    val descripcionState = viewModel.descripcion.value
    val salarioState = viewModel.salario.value
    var isError = viewModel.isError

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when (event){
                is OcupacionEditViewModel.UiEvent.SaveOcupacion -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(

        topBar = {
            OcupacionEditTopBar(
                topAppBarText = stringResource(id = R.string.addOcupacion)
            )
        },

        content = {
            viewModel.isError = Validacion(
                descripcion = descripcionState.text,
                salario = salarioState.text,
                onEvent = { viewModel.onEvent(it) })
        },

        bottomBar = {
            OcupacionEditBottomBar(
                onInsertOcupacion = { viewModel.onEvent(OcupacionEditEvent.InsertOcupacion)},
                isError = isError
            )
        }

    )
}

@Composable
fun OcupacionEditConten(
    descripcion: String,
    salario: String,
    errorDescripcion: Boolean,
    errorSalario: Boolean,
    msgDescripcion: String,
    msgSalario: String,
    onEvent: (OcupacionEditEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        InputText(
            isError = errorDescripcion,
            msgError = msgDescripcion,
            text = descripcion,
            label = stringResource(id = R.string.Descripcion),
            onTextChange = {onEvent(OcupacionEditEvent.EnteredDescripcion(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )

        InputText(
            isError = errorSalario,
            msgError = msgSalario,
            text = salario,
            label = stringResource(id = R.string.Salario),
            onTextChange = {onEvent(OcupacionEditEvent.EnteredSalario(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )
    }
}

@Composable
fun OcupacionEditBottomBar(
    modifier: Modifier = Modifier,
    onInsertOcupacion: () -> Unit,
    isError: Boolean
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 18.dp),
        shape = CircleShape,
        border= BorderStroke(1.dp, Color.Green),
        onClick = { onInsertOcupacion() },
        enabled = isError

    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null
        )
        Text(text = stringResource(id = R.string.addOcupacion))
    }
}

@Composable
fun OcupacionEditTopBar(topAppBarText: String) {
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
    descripcion: String,
    salario: String,
    onEvent: (OcupacionEditEvent) -> Unit
) : Boolean{

    var isErrorDescrition = false
    var isErrorSalario = false

    var msgErrorDescripcion = ""
    var msgErrorSalario = ""

    if (descripcion.length < 5 && descripcion.isNotEmpty()){
        isErrorDescrition = true;
        msgErrorDescripcion = "Caracteres insuficientes Mínimo, (5)";
    }
    else if (descripcion.isEmpty()){
        isErrorDescrition = true;
        msgErrorDescripcion = "*Campo Obligatorio*";
    }
    else if (!(descripcion.any{it.isLetter()})){
        isErrorDescrition = true;
        msgErrorDescripcion = "Descripcion no valida";
    }

    if (salario.isEmpty()){
        isErrorSalario = true
        msgErrorSalario = "*Campo Obligatorio*"
    }
    else if( !isNumeric(salario)) {
        isErrorSalario = true
        msgErrorSalario = "No es una cantidad valida"
    }

    OcupacionEditConten(
        descripcion = descripcion,
        salario = salario,
        errorDescripcion = isErrorDescrition,
        errorSalario = isErrorSalario,
        msgDescripcion = msgErrorDescripcion,
        msgSalario = msgErrorSalario,
        onEvent = {onEvent(it)}
    )

    return !(isErrorDescrition && isErrorSalario)
}