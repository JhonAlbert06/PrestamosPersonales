package edu.ucne.prestamospersonales.presentation.consulta.homePrestamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Persona
import edu.ucne.prestamospersonales.data.models.Prestamo
import edu.ucne.prestamospersonales.data.repository.PersonaRepositoryImpl
import edu.ucne.prestamospersonales.data.repository.PrestamoRepositoryImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PrestamoListUiState(
    val prestamos : List<Prestamo> = emptyList()
)

@HiltViewModel
class HomePrestamosViewModel @Inject constructor(
    private val Impl: PrestamoRepositoryImpl
): ViewModel(){

    var persona by mutableStateOf(Persona())

    private val _uiState = MutableStateFlow(PrestamoListUiState())
    val uiState : StateFlow<PrestamoListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            Impl.getAll().onEach { list ->
                _uiState.update {
                    it.copy(prestamos = list)
                }
            }
        }
    }

    fun delete(prestamo: Prestamo) {
        viewModelScope.launch {
            Impl.delete(prestamo)
        }
    }

}