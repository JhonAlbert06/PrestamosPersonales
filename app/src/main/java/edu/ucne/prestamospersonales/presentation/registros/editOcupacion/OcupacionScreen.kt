package edu.ucne.prestamospersonales.presentation.registros.editOcupacion

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
import androidx.navigation.NavHostController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.presentation.registros.editOcupacion.components.OcupacionInputText


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OcupacionScreen(
    navController: NavHostController,
    viewModel: EditOcupacionViewModel = hiltViewModel()
) {

    Scaffold(

        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.addOcupacion)
            )
        },

        content = {
            Validacion(viewModel)
        },

        bottomBar = {
            EditBottomBar(
                onInsertOcupacion = { viewModel.Save()},
                isError = viewModel.isError
            )
        }

    )
}

@Composable
fun EditBottomBar(
    modifier: Modifier = Modifier,
    onInsertOcupacion: () -> Unit,
    isError: Boolean = true
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
fun EditConten(viewModel: EditOcupacionViewModel) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        OcupacionInputText(
            isError = viewModel.isErrorDescription,
            errorMsg = viewModel.errorMsgDescripcion,
            text = viewModel._descripcion,
            hint = stringResource(id = R.string.Descripcion),
            onTextChange = {viewModel._descripcion = it} ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.clearFocus()
            })
        )

        OcupacionInputText(
            isError = viewModel.isErrorSalario,
            errorMsg = viewModel.errorMsgSalario,
            text = viewModel._salario,
            hint = stringResource(id = R.string.Salario),
            onTextChange = {viewModel._salario = it} ,
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

@Preview(showSystemUi = true)
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
fun Validacion(viewModel: EditOcupacionViewModel){

    if (viewModel._descripcion.length < 5 && viewModel._descripcion.isNotEmpty()){
        viewModel.isErrorDescription = true;
        viewModel.errorMsgDescripcion = "Caracteres insuficientes Mínimo, (5)";
    }else if (viewModel._descripcion.isEmpty()){
        viewModel.isErrorDescription= true;
        viewModel.errorMsgDescripcion = "*Campo Obligatorio*";
    }else if (!(viewModel._descripcion.any{it.isLetter()})){
        viewModel.isErrorDescription = true;
        viewModel.errorMsgDescripcion = "Descripcion no valida";
    }

    if (viewModel._salario.isEmpty()){
        viewModel.isErrorSalario = true
        viewModel.errorMsgSalario = "*Campo Obligatorio*"
    }else if( !isNumeric(viewModel._salario)) {
        viewModel.isErrorSalario = true
        viewModel.errorMsgSalario = "No es una cantidad valida"
    }

    viewModel.isError = !(viewModel.isErrorDescription || viewModel.isErrorSalario)
    EditConten(viewModel = viewModel)
}
