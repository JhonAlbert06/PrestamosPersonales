package edu.ucne.prestamospersonales.presentation.consulta.homePrestamo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Prestamo
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

    private val _uiState = MutableStateFlow(PrestamoListUiState())
    val uiState : StateFlow<PrestamoListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            Impl.getAll().onEach { list ->
                _uiState.getAndUpdate {
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