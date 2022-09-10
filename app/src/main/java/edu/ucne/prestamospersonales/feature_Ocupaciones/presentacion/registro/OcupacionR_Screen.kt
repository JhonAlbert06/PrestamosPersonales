package edu.ucne.prestamospersonales.feature_Ocupaciones.presentacion.registro

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.components.InputText
import java.lang.NumberFormatException


@Composable
fun OcupacionR_Screen(
    navController: NavController,
    viewModel: OcupacionR_ViewModel = hiltViewModel(),
){
    val descripcionState = viewModel.descripcion
    val salarioState = viewModel.salario
    var error = false

    Scaffold(

        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.addOcupacion)
            )
        },

        content = {
            error = validacion(
                descripcion = descripcionState,
                salario = salarioState,
                viewModel = viewModel
            )
        },

        bottomBar = {
            EditBottomBar(
                onInsertOcupacion = { viewModel.Save()},
                isError = error
            )
        }

    )
}

@Composable
fun EditBottomBar(
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
fun EditConten(
    descripcion: String,
    salario: String,
    isErrorDescription: Boolean = false,
    isErrorSalario: Boolean = false,
    errorMsgDescripcion: String = "",
    errorMsgSalario: String = "",
    viewModel: OcupacionR_ViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        InputText(
            isError = isErrorDescription,
            errorMsg= errorMsgDescripcion,
            text = descripcion,
            hint = stringResource(id = R.string.Descripcion),
            onTextChange = { viewModel.descripcion = it } ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.clearFocus()
            })
        )

        InputText(
            isError = isErrorSalario,
            errorMsg= errorMsgSalario,
            text = salario,
            hint = stringResource(id = R.string.Salario),
            onTextChange = { viewModel.salario = it } ,
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevieScreen(){


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
fun validacion(
    descripcion: String,
    salario: String,
    viewModel: OcupacionR_ViewModel
) : Boolean{
    var isErrorDescrition = false
    var isErrorSalario = false

    var errorDescripcion = ""
    var errorSalario = ""

    if (descripcion.length < 5 && descripcion.isNotEmpty()){
        isErrorDescrition = true;
        errorDescripcion = "Caracteres insuficientes Mínimo, (5)";
    }else if (descripcion.isEmpty()){
        isErrorDescrition = true;
        errorDescripcion = "*Campo Obligatorio*";
    }else if (!(descripcion.any{it.isLetter()})){
        isErrorDescrition = true;
        errorDescripcion = "Descripcion no valida";
    }

    if (salario.isEmpty()){
        isErrorSalario = true
        errorSalario = "*Campo Obligatorio*"
    }else if( !isNumeric(salario)) {
        isErrorSalario = true
        errorSalario = "No es una cantidad valida"
    }

    EditConten(
        descripcion = descripcion,
        salario = salario,
        isErrorDescription= isErrorDescrition,
        isErrorSalario= isErrorSalario,
        errorMsgDescripcion = errorDescripcion,
        errorMsgSalario = errorSalario,
        viewModel = viewModel
    )

    return !(isErrorDescrition || isErrorSalario)
}
