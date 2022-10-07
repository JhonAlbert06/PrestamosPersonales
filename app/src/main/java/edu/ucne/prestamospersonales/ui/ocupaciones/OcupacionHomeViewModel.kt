package edu.ucne.prestamospersonales.ui.ocupaciones

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.repository.OcupacionRepositoryImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionHomeViewModel @Inject constructor(
    private val impl: OcupacionRepositoryImpl
) : ViewModel() {

    private val _state = mutableStateOf(OcupacionHomeState())
    var state: State<OcupacionHomeState> = _state

    init {
        impl.getAll().onEach { ocupaciones ->
            _state.value = state.value.copy(ocupaciones = ocupaciones)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: OcupacionHomeEvent){
        when (event){
            is OcupacionHomeEvent.DeleteOcupacion -> {
                viewModelScope.launch {
                    impl.delete(event.ocupacion)
                }
            }
        }
    }

}