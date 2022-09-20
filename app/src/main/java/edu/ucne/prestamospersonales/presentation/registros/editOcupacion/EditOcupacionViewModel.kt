package edu.ucne.prestamospersonales.presentation.registros.editOcupacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Ocupacion
import edu.ucne.prestamospersonales.data.repository.OcupacionRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditOcupacionViewModel @Inject constructor(
    private val Impl: OcupacionRepositoryImpl
): ViewModel() {

    var _descripcion by mutableStateOf("")
    var _salario by mutableStateOf("")

    var isErrorDescription: Boolean = false
    var isErrorSalario: Boolean = false
    var errorMsgDescripcion: String = ""
    var errorMsgSalario: String = ""


    var isError: Boolean = false

    fun Save(){
        viewModelScope.launch {
            Impl.insert(
                Ocupacion(
                    descripcion = _descripcion,
                    salario = _salario.toDouble()
                )
            )
        }
    }
}