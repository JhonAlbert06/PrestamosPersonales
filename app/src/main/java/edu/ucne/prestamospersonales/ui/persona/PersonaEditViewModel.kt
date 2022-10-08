package edu.ucne.prestamospersonales.ui.persona

import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Persona
import edu.ucne.prestamospersonales.data.repository.PersonaRepositoryImpl
import edu.ucne.prestamospersonales.ui.ocupaciones.OcupacionEditViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonaEditViewModel @Inject constructor(
    private val impl: PersonaRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _nombres = mutableStateOf(TextFieldPersonaState())
    val nombres: State<TextFieldPersonaState> = _nombres

    private val _telefono = mutableStateOf(TextFieldPersonaState())
    val telefono: State<TextFieldPersonaState> = _telefono

    private val _celular = mutableStateOf(TextFieldPersonaState())
    val celular: State<TextFieldPersonaState> = _celular

    private val _email = mutableStateOf(TextFieldPersonaState())
    val email: State<TextFieldPersonaState> = _email

    private val _direccion = mutableStateOf(TextFieldPersonaState())
    val direccion: State<TextFieldPersonaState> = _direccion

    private val _fechaNacimiento = mutableStateOf(TextFieldPersonaState())
    val fechaNacimiento: State<TextFieldPersonaState> = _fechaNacimiento

    private val _ocupacionId = mutableStateOf(TextFieldPersonaState())
    val ocupacionId: State<TextFieldPersonaState> = _ocupacionId

    private val _balance = mutableStateOf(TextFieldPersonaState())
    val balance: State<TextFieldPersonaState> = _balance

    var isError = false

    private var currentUserId: Int? = null

    private val _eventFlow = MutableSharedFlow<PersonaEditViewModel.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("personaId")?.let{ personaId ->

            if (personaId != -1){
                viewModelScope.launch {
                    impl.getById(personaId)?.also { persona ->
                        currentUserId = persona.personaId

                        _nombres.value = nombres.value.copy(text = persona.nombres)
                        _telefono.value = telefono.value.copy(text = persona.telefono)
                        _celular.value = celular.value.copy(text = persona.celular)
                        _email.value = email.value.copy(text = persona.email)
                        _direccion.value = direccion.value.copy(text = persona.direccion)
                        _fechaNacimiento.value = fechaNacimiento.value.copy(text = persona.fechaNacimiento)
                        _ocupacionId.value = ocupacionId.value.copy(text = persona.ocupacionId.toString())
                        _balance.value = balance.value.copy(text = persona.balance.toString())
                    }
                }
            }

        }
    }

    fun onEvent(event: PersonaEditEvent){

        when(event){
            is PersonaEditEvent.EnteredNombres -> {
                _nombres.value = nombres.value.copy(text = event.value)
            }

            is PersonaEditEvent.EnteredTelefono -> {
                _telefono.value = telefono.value.copy(text = event.value)
            }

            is PersonaEditEvent.EnteredCelular -> {
                _celular.value = celular.value.copy(text = event.value)
            }

            is PersonaEditEvent.EnteredEmail -> {
                _email.value = email.value.copy(text = event.value)
            }

            is PersonaEditEvent.EnteredDireccion -> {
                _direccion.value = direccion.value.copy(text = event.value)
            }

            is PersonaEditEvent.EnteredFechaNacimiento -> {
                _fechaNacimiento.value = fechaNacimiento.value.copy(text = event.value)
            }

            is PersonaEditEvent.EnteredOculacionId -> {
                _ocupacionId.value = ocupacionId.value.copy(text = event.value)
            }

            is PersonaEditEvent.EnteredBalance -> {
                _balance.value = balance.value.copy(text = event.value)
            }

            PersonaEditEvent.InsertPersona -> {
                viewModelScope.launch {
                    impl.insert(
                        Persona(
                            nombres = _nombres.value.text,
                            telefono = _telefono.value.text,
                            celular = _celular.value.text,
                            email = _email.value.text,
                            direccion = _direccion.value.text,
                            fechaNacimiento = _fechaNacimiento.value.text,
                            ocupacionId = _ocupacionId.value.text.toInt(),
                            balance = 0.00,//_balance.value.text.toDouble(),
                            personaId = currentUserId
                        )
                    )
                    _eventFlow.emit(UiEvent.SavePersona)
                }
            }
        }
    }

    sealed class UiEvent{
        object SavePersona: UiEvent()
    }

}