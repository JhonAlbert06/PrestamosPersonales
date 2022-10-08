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
import androidx.compose.runtime.LaunchedEffect
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
import edu.ucne.prestamospersonales.ui.components.ComboBoxPersona
import edu.ucne.prestamospersonales.ui.components.ComboFecha
import edu.ucne.prestamospersonales.ui.components.InputText
import edu.ucne.prestamospersonales.ui.persona.isNumeric
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PrestamoEditScreen(
    navController: NavController,
    viewModel: PrestamoEditViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PrestamoEditViewModel.UiEvent.SavePrestamo -> {
                    navController.navigateUp()
                }
            }
        }
    }


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
    isErrorFechaPrestamo:Boolean,
    isErrorFechaVence:Boolean,
    isErrorPersonaId:Boolean,
    isErrorConcepto:Boolean,
    isErrorMonto:Boolean,
    isErrorBalance:Boolean,
    mgsFechaPrestamo:String,
    mgsFechaVence:String,
    mgsPersonaId:String,
    mgsConcepto:String,
    mgsMonto:String,
    mgsBalance:String,
    onEvent: (PrestamoEditEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        viewModel.fechaPrestamo.value.text = ComboFecha(isErrorFechaPrestamo, mgsFechaPrestamo)
        viewModel.fechaVence.value.text = ComboFecha(isErrorFechaVence, mgsFechaVence)
        viewModel.personaId.value.text = ComboBoxPersona(isErrorPersonaId, mgsPersonaId).toString()

        InputText(
            isError = isErrorConcepto,
            msgError = mgsConcepto,
            text = viewModel.concepto.value.text,
            label = stringResource(id = R.string.Concepto),
            onTextChange = { onEvent(PrestamoEditEvent.EnteredConcepto(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )

        InputText(
            isError = isErrorMonto,
            msgError = mgsMonto,
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
            isError = isErrorBalance,
            msgError = mgsBalance,
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

    var isErrorFechaPrestamo = false
    var isErrorFechaVence = false
    var isErrorPersonaId = false
    var isErrorConcepto = false
    var isErrorMonto = false
    var isErrorBalance = false

    var mgsFechaPrestamo = ""
    var mgsFechaVence = ""
    var mgsPersonaId = ""
    var mgsConcepto = ""
    var mgsMonto = ""
    var mgsBalance = ""

    if (viewModel.fechaPrestamo.value.text.isBlank()) {
        isErrorFechaPrestamo = true
        mgsFechaPrestamo = "*Campo Obligatorio*"
    }

    if (viewModel.fechaVence.value.text.isBlank()) {
        isErrorFechaVence = true
        mgsFechaVence = "*Campo Obligatorio*"
    }

    if (viewModel.personaId.value.text.isBlank()) {
        isErrorPersonaId = true
        mgsPersonaId = "*Campo Obligatorio*"
    }

    if (viewModel.concepto.value.text.isEmpty()) {
        isErrorConcepto = true
        mgsConcepto = "*Campo Obligatorio*"
    } else if (viewModel.concepto.value.text.isDigitsOnly()) {
        isErrorConcepto = true
        mgsConcepto = "Solo se permiten Caracteres"
    } else if (!(viewModel.concepto.value.text.any { it.isLetter() })) {
        isErrorConcepto = true
        mgsConcepto = "No se permiten simbolos"
    }

    if (viewModel.monto.value.text.isEmpty()) {
        isErrorMonto = true
        mgsMonto = "*Campo Obligatorio*"
    } else if (!isNumeric(viewModel.monto.value.text)) {
        isErrorMonto = true
        mgsMonto = "Monto no valido"
    }

    if (viewModel.balance.value.text.isEmpty()) {
        isErrorBalance = true
        mgsBalance = "*Campo Obligatorio*"
    } else if (!isNumeric(viewModel.balance.value.text)) {
        isErrorBalance = true
        mgsBalance = "Balance no valido"
    }


    PrestamoEditConten(
        viewModel = viewModel,
        isErrorFechaPrestamo = isErrorFechaPrestamo,
        isErrorFechaVence = isErrorFechaVence,
        isErrorPersonaId = isErrorPersonaId,
        isErrorConcepto = isErrorConcepto,
        isErrorMonto = isErrorMonto,
        isErrorBalance = isErrorBalance,
        mgsFechaPrestamo = mgsFechaPrestamo,
        mgsFechaVence = mgsFechaVence,
        mgsPersonaId = mgsPersonaId,
        mgsConcepto = mgsConcepto,
        mgsMonto = mgsMonto,
        mgsBalance = mgsBalance,
        onEvent = onEvent
    )


    return (true)
}