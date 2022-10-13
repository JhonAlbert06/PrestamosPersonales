package edu.ucne.prestamospersonales.ui.components

import android.content.Context
import androidx.compose.runtime.Composable

@Composable
fun Toast(context: Context, mgs: String, isLoadig: Boolean) {

    if (isLoadig == true){
        android.widget.Toast.makeText(context, "Buscando Articulos", android.widget.Toast.LENGTH_LONG).show()
    }
    else {
        android.widget.Toast.makeText(context, mgs, android.widget.Toast.LENGTH_LONG).show()
    }

}