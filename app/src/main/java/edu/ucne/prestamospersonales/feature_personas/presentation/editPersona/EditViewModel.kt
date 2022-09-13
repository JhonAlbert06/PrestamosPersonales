package edu.ucne.prestamospersonales.feature_personas.presentation.editPersona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.navArgument
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.model.Persona
import edu.ucne.prestamospersonales.feature_personas.domain.repository.PersonaRepositoryImpl
import edu.ucne.prestamospersonales.feature_personas.domain.uses_cases.Delete
import edu.ucne.prestamospersonales.feature_personas.domain.uses_cases.GetAll
import edu.ucne.prestamospersonales.feature_personas.domain.uses_cases.GetById
import edu.ucne.prestamospersonales.feature_personas.domain.uses_cases.Insert
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private var insert: Insert,

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
            insert(
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