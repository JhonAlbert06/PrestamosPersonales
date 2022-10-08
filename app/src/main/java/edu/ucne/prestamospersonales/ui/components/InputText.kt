package edu.ucne.prestamospersonales.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InputText(
    label: String,
    text: String,
    isError: Boolean = false,
    msgError: String = "",
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    readOnly: Boolean = false
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .padding(
                bottom = if (isError){
                    0.dp
                }else{10.dp}
            )
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text(text = label) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            readOnly = readOnly
        )

        if (isError) {
            Text(
                text = msgError,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

    }
}
