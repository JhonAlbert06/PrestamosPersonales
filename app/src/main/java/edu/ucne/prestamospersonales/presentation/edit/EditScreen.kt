package edu.ucne.prestamospersonales.presentation.edit

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
import edu.ucne.prestamospersonales.presentation.edit.components.OcupacionInputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditScreen(
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel()
){
    val descripcionState = viewModel.descripcion.value
    val salarioState = viewModel.salario.value

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when (event){
                is EditViewModel.UiEvent.SaveOcupacion -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(

        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.addOcupacion)
            )
        },

        content = {
            EditConten(
                descripcion = descripcionState.text,
                salario = salarioState.text,
                onEvent = { viewModel.onEvent(it) }
            )
        },

        bottomBar = {

            EditBottomBar(

                onInsertOcupacion = { viewModel.onEvent(EditEvent.InsertOcupacion)}
            )
        }

    )
}

fun validar(){

}

@Composable
fun EditBottomBar(
    modifier: Modifier = Modifier,
    onInsertOcupacion: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 18.dp),
            shape = CircleShape,
            border= BorderStroke(1.dp, Color.Green),
            onClick = { onInsertOcupacion() }
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null
        )
        Text(text = stringResource(id = R.string.addOcupacion))
    }
}

@Composable
fun EditConten(
    descripcion: String,
    salario: String,
    onEvent: (EditEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        OcupacionInputText(
            text = descripcion,
            hint = stringResource(id = R.string.Descripcion),
            onTextChange = {onEvent(EditEvent.EnteredDescripcion(it))} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.clearFocus()
            })
        )

        OcupacionInputText(
            text = salario,
            hint = stringResource(id = R.string.Salario),
            onTextChange = {onEvent(EditEvent.EnteredSalario(it))} ,
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
