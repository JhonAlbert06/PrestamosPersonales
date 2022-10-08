package edu.ucne.prestamospersonales.ui.persona

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.repository.PersonaRepositoryImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonaHomeViewModel @Inject constructor(
    private val impl: PersonaRepositoryImpl
): ViewModel() {
    private val _state = mutableStateOf(PersonaHomeState())
    var state: State<PersonaHomeState> = _state

    init {
        impl.getAll().onEach { personas ->
            _state.value = state.value.copy(personas = personas)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: PersonaHomeEvent){
        when (event){
            is PersonaHomeEvent.DeletePersona -> {
                viewModelScope.launch {
                    impl.delete(event.persona)
                }
            }
        }
    }

}