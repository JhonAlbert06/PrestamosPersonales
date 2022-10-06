package edu.ucne.prestamospersonales.presentation.registros.editPersona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Persona
import edu.ucne.prestamospersonales.data.repository.OcupacionRepositoryImpl
import edu.ucne.prestamospersonales.data.repository.PersonaRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPersonaViewModel @Inject constructor(
    private val Impl: PersonaRepositoryImpl
) : ViewModel() {

    var isError by mutableStateOf(false)

    var nombres by mutableStateOf("")
    var telefono by mutableStateOf("")
    var celular by mutableStateOf("")
    var email by mutableStateOf("")
    var direccion by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var ocupacionId by mutableStateOf("")

    var isErrorNombres: Boolean = false
    var isErrorTelefono: Boolean = false
    var isErrorCelular: Boolean = false
    var isErrorEmail: Boolean = false
    var isErrorDireccion: Boolean = false

    var errorMgsNombres: String = ""
    var errorMgsTelefono: String = ""
    var errorMgsCelular: String = ""
    var errorMgsEmail: String = ""
    var errorMgsDireccion: String = ""

    fun save() {
        viewModelScope.launch {
            Impl.insert(
                persona = Persona(
                    nombres = nombres,
                    telefono = telefono,
                    celular = celular,
                    email =  email,
                    direccion = direccion,
                    fechaNacimiento = fechaNacimiento,
                    ocupacionId = ocupacionId.toInt()
                )
            )
        }
    }
}