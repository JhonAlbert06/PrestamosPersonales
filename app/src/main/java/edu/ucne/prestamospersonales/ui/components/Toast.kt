package edu.ucne.prestamospersonales.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Toast(context: Context, mgs: String, isLoadig: Boolean) {

    if (isLoadig) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(bottom = 100.dp)
                    .height(50.dp)
                    .width(50.dp)

            )
        }
    } else if (isLoadig || mgs != ""){
        android.widget.Toast.makeText(context, mgs, android.widget.Toast.LENGTH_LONG).show()
    }

}