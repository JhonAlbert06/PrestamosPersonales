package edu.ucne.prestamospersonales.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.domain.use_cases.DeleteOcupacion
import edu.ucne.prestamospersonales.domain.use_cases.GetOcupaciones
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val deleteOcupacion: DeleteOcupacion,
                getOcupaciones: GetOcupaciones
): ViewModel() {
    private val _state = mutableStateOf(HomeState())
    var state: State<HomeState> = _state

    init{
        getOcupaciones().onEach { ocupaciones ->
            _state.value = state.value.copy(ocupaciones = ocupaciones)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DeleteOcupacion -> {
                viewModelScope.launch {
                    deleteOcupacion(event.ocupacion)
                }
            }
        }
    }
}