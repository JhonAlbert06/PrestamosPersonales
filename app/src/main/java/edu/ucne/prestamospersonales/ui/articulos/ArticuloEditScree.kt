package edu.ucne.prestamospersonales.ui.articulos

import android.annotation.SuppressLint
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
import androidx.compose.ui.*
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
import androidx.navigation.NavHostController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.ui.components.InputText
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ArticuloEditScreen(
    navController: NavHostController,
    viewModel: ArticuloEditViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when (event){
                is ArticuloEditViewModel.UiEvent.SaveArticulo -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(

        topBar = {
            EditAuxTopBar(
                topAppBarText = stringResource(id = R.string.addArticulo)
            )
        },

        content = {
            viewModel.isError.value = validacion(
                viewModel = viewModel,
                onEvent = { viewModel.onEven(it) }
            )
        },

        bottomBar = {
            ArticuloEditBottomBar(
                onInsertArticulo = { viewModel.onEven(ArticuloEditEvent.InsertArticulo) },
                isError = true// viewModel.isError
            )
        }

    )
}

@Composable
fun ArticuloEditBottomBar(
    modifier: Modifier = Modifier,
    onInsertArticulo: () -> Unit,
    isError: Boolean
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 18.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Green),
        onClick = { onInsertArticulo() },
        enabled = isError

    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null
        )
        Text(text = stringResource(id = R.string.addArticulo))
    }
}

@Composable
fun EditConten(
    viewModel: ArticuloEditViewModel,
    isErrorPrecio: Boolean,
    isErrorExistencia: Boolean,
    isErrorMarca: Boolean,
    isErrorDescripcion: Boolean,
    mgsIsErrorPrecio: String,
    mgsIsErrorExistencia: String,
    mgsIrrorMarca: String,
    mgsIrrorDescripcion: String,
    onEvent: (ArticuloEditEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))
        InputText(
            isError = isErrorDescripcion,
            msgError = mgsIrrorDescripcion,
            text = viewModel.descripcion.value.text,
            label = stringResource(id = R.string.Descripcion),
            onTextChange = {onEvent(ArticuloEditEvent.EnteredDescripcion(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )
        Spacer(modifier = Modifier.padding(5.dp))
        InputText(
            isError = isErrorMarca,
            msgError = mgsIrrorMarca,
            text = viewModel.marca.value.text,
            label = stringResource(id = R.string.Marca),
            onTextChange = {onEvent(ArticuloEditEvent.EnteredMarca(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )
        Spacer(modifier = Modifier.padding(5.dp))
        InputText(
            isError = isErrorPrecio,
            msgError = mgsIsErrorPrecio,
            text = viewModel.precio.value.text,
            label = stringResource(id = R.string.Precio),
            onTextChange = {onEvent(ArticuloEditEvent.EnteredPrecio(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(focusDirection = FocusDirection.Down)
            })
        )
        Spacer(modifier = Modifier.padding(5.dp))
        InputText(
            isError = isErrorExistencia,
            msgError = mgsIsErrorExistencia,
            text = viewModel.existencia.value.text,
            label = stringResource(id = R.string.Existencia),
            onTextChange = {onEvent(ArticuloEditEvent.EnteredExistencia(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.clearFocus()
            })
        )
        Spacer(modifier = Modifier.padding(5.dp))
    }

}

@Composable
fun EditAuxTopBar(topAppBarText: String) {

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

fun isNumber(aux: String): Boolean {
    return try {
        aux.toDouble()
        false
    } catch (e: java.lang.NumberFormatException) {
        true
    }
}

@Composable
fun validacion(
    viewModel: ArticuloEditViewModel,
    onEvent: (ArticuloEditEvent) -> Unit
): Boolean {

    var isErrorPrecio = false
    var isErrorExistencia = false
    var isErrorMarca = false
    var isErrorDescripcion = false
    var mgsIsErrorPrecio = ""
    var mgsIsErrorExistencia = ""
    var mgsIrrorMarca = ""
    var mgsIrrorDescripcion = ""

    if (viewModel.descripcion.value.text.isBlank()) {
        isErrorDescripcion = true
        mgsIrrorDescripcion = "*Campo Obligatorio*"
    } else if (viewModel.descripcion.value.text.isDigitsOnly()) {
        isErrorDescripcion = true
        mgsIrrorDescripcion = "*Descripcion invalidad(Solo puede contener letras)*"
    } else if (viewModel.descripcion.value.text.length in 1..4) {
        isErrorDescripcion = true
        mgsIrrorDescripcion = "*La descripcion debe contener minimo(5) Caracteres*"
    }

    if (viewModel.marca.value.text.isBlank()) {
        isErrorMarca = true
        mgsIrrorMarca = "*Campo Obligatorio*"
    } else if (viewModel.marca.value.text.isDigitsOnly()) {
        isErrorMarca = true
        mgsIrrorMarca = "*Descripcion invalidad(Solo puede contener letras)*"
    } else if (viewModel.marca.value.text.length in 1..4) {
        isErrorMarca = true
        mgsIrrorMarca = "*La descripcion debe contener minimo(5) Caracteres*"
    }

    if (viewModel.precio.value.text.isBlank()) {
        isErrorPrecio = true
        mgsIsErrorPrecio = "*Campo Obligatorio*"
    } else if (isNumber(viewModel.precio.value.text)) {
        isErrorPrecio = true
        mgsIsErrorPrecio = "*Esto no es un Numero*"
    }

    if (viewModel.existencia.value.text.isBlank()) {
        isErrorExistencia = true
        mgsIsErrorExistencia = "*Campo Obligatorio*"
    } else if (isNumber(viewModel.existencia.value.text)) {
        isErrorExistencia = true
        mgsIsErrorExistencia = "*Esto no es un Numero*"
    }

    EditConten(
        viewModel = viewModel,
        isErrorPrecio = isErrorPrecio,
        isErrorExistencia = isErrorExistencia,
        isErrorMarca = isErrorMarca,
        isErrorDescripcion = isErrorDescripcion,
        mgsIsErrorPrecio = mgsIsErrorPrecio,
        mgsIsErrorExistencia = mgsIsErrorExistencia,
        mgsIrrorMarca = mgsIrrorMarca,
        mgsIrrorDescripcion = mgsIrrorDescripcion,
        onEvent = {onEvent(it)}
    )

    return !(isErrorPrecio || isErrorExistencia || isErrorMarca || isErrorDescripcion)
}