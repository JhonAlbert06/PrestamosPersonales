package edu.ucne.prestamospersonales.ui.prestamo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.repository.PrestamoRepositoryImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrestamoHomeViewModel @Inject constructor(
    private val impl: PrestamoRepositoryImpl
) : ViewModel() {

    private val _state = mutableStateOf(PrestamoHomeState())
    var state: State<PrestamoHomeState> = _state

    init {
        impl.getAll().onEach { prestamos ->
            _state.value = state.value.copy(prestamos = prestamos)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: PrestamoHomeEvent){
        when (event){
            is PrestamoHomeEvent.DeletePrestamo -> {
                viewModelScope.launch {
                    impl.delete(event.prestamo)
                }
            }
        }
    }

}