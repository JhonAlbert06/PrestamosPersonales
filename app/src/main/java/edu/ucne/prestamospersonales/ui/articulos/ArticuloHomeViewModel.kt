package edu.ucne.prestamospersonales.ui.articulos

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.remote.ArticuloRepository
import edu.ucne.prestamospersonales.utils.Resourse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ArticuloHomeViewModel @Inject constructor(
    private val api: ArticuloRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticuloHomeState())
    val uiState: StateFlow<ArticuloHomeState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(articulos = api.getArticulos(), isLoading = false)
                }catch (ioe: IOException){
                    val mgs = ioe.message.toString()
                    it.copy(error = mgs, isLoading = false)
                }
            }
        }
    }

    fun onEvent(event: ArticuloHomeEvent) {
        when (event) {
            is ArticuloHomeEvent.DeleteArticulo -> {
                viewModelScope.launch {
                    api.deleteArticulo(event.articulo.ariticuloId.toString())
                }
            }
        }
    }

}
