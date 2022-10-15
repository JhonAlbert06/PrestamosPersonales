package edu.ucne.prestamospersonales.ui.articulos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.remote.ArticuloRepository
import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticuloEditViewModel @Inject constructor(
    private val api: ArticuloRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _descripcion = mutableStateOf(TextFieldArticuloState())
    val descripcion : State<TextFieldArticuloState> = _descripcion

    private val _marca = mutableStateOf(TextFieldArticuloState())
    val marca : State<TextFieldArticuloState> = _marca

    private val _precio = mutableStateOf(TextFieldArticuloState())
    val precio : State<TextFieldArticuloState> = _precio

    private val _existencia = mutableStateOf(TextFieldArticuloState())
    val existencia : State<TextFieldArticuloState> = _existencia

    var isError = mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentUserId: String? = null

    init {
        savedStateHandle.get<String>("ariticuloId")?.let { articuloid ->
            if (articuloid.toInt() != -1){
                viewModelScope.launch {
                    api.getArticulo(articuloid)?.also { articulo ->
                        currentUserId = articulo.ariticuloId.toString()

                        _descripcion.value = descripcion.value.copy(text = articulo.descripcion)
                        _marca.value = marca.value.copy(text = articulo.marca)
                        _precio.value = precio.value.copy(text = articulo.precio.toString())
                        _existencia.value = existencia.value.copy(text = articulo.existencia.toString())
                    }
                }
            }
        }
    }

    fun onEven(event: ArticuloEditEvent){
        when (event){
            is ArticuloEditEvent.EnteredDescripcion -> {
                _descripcion.value = descripcion.value.copy(text = event.value)
            }

            is ArticuloEditEvent.EnteredMarca -> {
                _marca.value = marca.value.copy(text = event.value)
            }

            is ArticuloEditEvent.EnteredPrecio -> {
                _precio.value = precio.value.copy(text = event.value)
            }

            is ArticuloEditEvent.EnteredExistencia -> {
                _precio.value = precio.value.copy(text = event.value)
            }

            ArticuloEditEvent.InsertArticulo -> {
                viewModelScope.launch {
                    api.createArticulo(
                        ArticulosResponseDto(
                            descripcion = _descripcion.value.text,
                            marca = _marca.value.text,
                            precio = _precio.value.text.toDouble(),
                            existencia = _existencia.value.text.toDouble()
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveArticulo)
                }
            }
        }
    }

    sealed class UiEvent{
        object SaveArticulo: UiEvent()
    }
}