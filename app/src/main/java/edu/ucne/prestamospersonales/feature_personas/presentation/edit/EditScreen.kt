package edu.ucne.prestamospersonales.feature_personas.presentation.edit


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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.feature_personas.presentation.components.ElijeFecha
import edu.ucne.prestamospersonales.feature_personas.presentation.components.InputText
import edu.ucne.prestamospersonales.feature_personas.presentation.components.TextBox
import edu.ucne.prestamospersonales.ui.theme.PrestamosPersonalesTheme
import java.lang.NumberFormatException

@Composable
fun EditScreen(
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel()
) {
    var error = false

    Scaffold(

        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.addPersona)
            )
        },

        content = {
            error = validacion(viewModel)
            //EditConten(viewModel)
        },

        bottomBar = {
            EditBottomBar(
                onInsertOcupacion = { viewModel.save() },
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
        border = BorderStroke(1.dp, Color.Green),
        onClick = { onInsertOcupacion() },
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
    viewModel: EditViewModel,
    isErrorNombres: Boolean = false,
    isErrorTelefono: Boolean = false,
    isErrorCelular: Boolean =  false,
    isErrorEmail: Boolean =  false,
    isErrorDireccion: Boolean = false,
    errorMgsNombres: String = "",
    errorMgsTelefono: String = "",
    errorMgsCelular: String = "",
    errorMgsEmail: String = "",
    errorMgsDireccion: String = ""
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        Spacer(modifier = Modifier.height(44.dp))

        InputText(
            isError = isErrorNombres,
            errorMsg = errorMgsNombres,
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
            isError = isErrorTelefono,
            errorMsg = errorMgsTelefono,
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
            isError = isErrorCelular,
            errorMsg = errorMgsCelular,
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
            isError = isErrorEmail,
            errorMsg = errorMgsEmail,
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
            isError = isErrorDireccion,
            errorMsg = errorMgsDireccion,
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
fun validacion(viewModel: EditViewModel): Boolean {

    var isErrorNombres = false
    var isErrorTelefono = false
    var isErrorCelular = false
    var isErrorEmail = false
    var isErrorDireccion = false

    var errorNombres = ""
    var errorTelefono = ""
    var errorCelular = ""
    var errorEmail = ""
    var errorDireccion = ""

    // Validacion de Nombres



    if (viewModel.telefono.isEmpty()) {
        isErrorCelular = true
        errorCelular = "*Campo Obligatorio*"
    } else if(viewModel.telefono.length < 10 && viewModel.telefono.isNotEmpty()) {
        isErrorCelular = true
        errorCelular = "Minimo 10 Numeros"
    } else if(!isNumeric(viewModel.telefono)) {
        isErrorCelular = true
        errorCelular = "No es una Numero"
    }

    if (viewModel.celular.isEmpty()) {
        isErrorCelular = true
        errorCelular = "*Campo Obligatorio*"
    } else if(viewModel.celular.length < 10 && viewModel.celular.isNotEmpty()) {
        isErrorCelular = true
        errorCelular = "Minimo (10) Numeros"
    } else if(!isNumeric(viewModel.celular)) {
        isErrorCelular = true
        errorCelular = "No es una Numero"
    }

    if (viewModel.email.isBlank())
    {
        isErrorEmail = true;
        errorEmail = "El campo esta vacio"
    }
    else if (
        !Patterns.EMAIL_ADDRESS
            .matcher(viewModel.email)
            .matches()
    ){
        isErrorEmail = true;
        errorEmail = "El Email no es valido"
    }

    if (viewModel.direccion.length < 5 && viewModel.direccion.isNotEmpty()){
        isErrorDireccion = true;
        errorDireccion = "Caracteres insuficientes Mínimo, (5)";
    }else if (viewModel.direccion.isEmpty()){
        isErrorDireccion = true;
        errorDireccion = "*Campo Obligatorio*";
    }else if (!(viewModel.direccion.any{it.isLetter()})){
        isErrorDireccion = true;
        errorDireccion = "Direccion no valida";
    }

    EditConten(
        viewModel = viewModel,
        isErrorNombres = isErrorNombres,
        isErrorTelefono = isErrorTelefono,
        isErrorCelular = isErrorCelular,
        isErrorEmail = isErrorEmail,
        isErrorDireccion = isErrorDireccion,
        errorMgsNombres = errorNombres,
        errorMgsTelefono = errorTelefono,
        errorMgsCelular = errorCelular,
        errorMgsEmail = errorEmail,
        errorMgsDireccion = errorDireccion
    )

return !(
            isErrorNombres ||
            isErrorTelefono ||
            isErrorCelular ||
            isErrorEmail ||
            isErrorDireccion
        )
}



@Preview(showSystemUi = true)
@Composable
fun PrevieScreen() {
    PrestamosPersonalesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            var isErrorDescription: Boolean = false
            var isErrorSalario: Boolean = false
            var errorMsgDescripcion: String = ""
            var errorMsgSalario: String = ""

            Scaffold(

                topBar = {
                    EditTopBar(
                        topAppBarText = stringResource(id = R.string.addPersona)
                    )
                },

                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val focusManager = LocalFocusManager.current

                        Spacer(modifier = Modifier.height(44.dp))

                        InputText(
                            isError = isErrorDescription,
                            errorMsg = errorMsgDescripcion,
                            text = "Jhon Albert",
                            hint = stringResource(id = R.string.Nombres),
                            onTextChange = { },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            })
                        )

                        InputText(
                            isError = isErrorSalario,
                            errorMsg = errorMsgSalario,
                            text = "8092907606",
                            hint = stringResource(id = R.string.Telefono),
                            onTextChange = { },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            })
                        )

                        InputText(
                            isError = isErrorSalario,
                            errorMsg = errorMsgSalario,
                            text = "8094566132",
                            hint = stringResource(id = R.string.Celular),
                            onTextChange = { },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            })
                        )

                        InputText(
                            isError = isErrorSalario,
                            errorMsg = errorMsgSalario,
                            text = "gjhon7778@gamil.com",
                            hint = stringResource(id = R.string.Email),
                            onTextChange = { },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            })
                        )

                        InputText(
                            isError = isErrorSalario,
                            errorMsg = errorMsgSalario,
                            text = "Calle C #31",
                            hint = stringResource(id = R.string.Direccion),
                            onTextChange = { },
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
                                .padding(16.dp)
                        ) {
                            ElijeFecha()
                            TextBox().toString()
                        }

                    }
                },

                bottomBar = {
                    EditBottomBar(
                        onInsertOcupacion = { },
                        isError = false
                    )
                }

            )

        }
    }

}
