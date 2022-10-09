package edu.ucne.prestamospersonales.ui.articulos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.remote.ArticuloRepository
import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ArticuloListUiState(
    val articulos : List<ArticulosResponseDto> = emptyList()
)


@HiltViewModel
class ArticuloHomeViewModel @Inject constructor(
    private val api: ArticuloRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ArticuloListUiState())
    val uiState : StateFlow<ArticuloListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            api.getAll().onEach { list ->
                _uiState.getAndUpdate {
                    it.copy(articulos = list)
                }
            }
        }
    }


}