package edu.ucne.prestamospersonales.feature_personas.presentation.editPersona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.prestamospersonales.data.model.Persona
import edu.ucne.prestamospersonales.feature_personas.domain.repository.PersonaRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditViewModel @Inject constructor(
    val repository: PersonaRepository
) : ViewModel() {

    var nombres by mutableStateOf("")
    var telefono by mutableStateOf("")
    var celular by mutableStateOf("")
    var email by mutableStateOf("")
    var direccion by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var ocupacionId by mutableStateOf("")

    fun save() {
        viewModelScope.launch {
            repository.insert(
                persona = Persona(
                    nombres = nombres,
                    telefono = telefono.toLong(),
                    celular = celular.toLong(),
                    email =  email,
                    direccion = direccion,
                    fechaNacimiento = fechaNacimiento,
                    ocupacionId = ocupacionId.toInt()
                )
            )
        }
    }
}