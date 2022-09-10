package edu.ucne.prestamospersonales.feature_Ocupaciones.presentacion.registro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.prestamospersonales.data.model.Ocupacion
import edu.ucne.prestamospersonales.feature_Ocupaciones.domain.repository.OcupacionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class OcupacionR_ViewModel @Inject constructor(
    val repository: OcupacionRepository
) : ViewModel() {

    var descripcion by mutableStateOf("")
    var salario by mutableStateOf("")

    fun Save(){
        viewModelScope.launch {
            repository.insert(
                ocupacion = Ocupacion(
                    descripcion = descripcion,
                    salario = salario.toDouble()
                )
            )
        }
    }


}