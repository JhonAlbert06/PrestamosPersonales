package edu.ucne.prestamospersonales.ui.ocupaciones

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Ocupacion
import edu.ucne.prestamospersonales.data.repository.OcupacionRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionEditViewModel @Inject constructor(
    private val impl: OcupacionRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _descripcion = mutableStateOf(TextFieldOcupacionState())
    val descripcion : State<TextFieldOcupacionState> = _descripcion

    private val _salario = mutableStateOf(TextFieldOcupacionState())
    val salario: State<TextFieldOcupacionState> = _salario

    var isError: Boolean = false

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentUserId: Int? = null

    init {
        savedStateHandle.get<Int>("ocupacionId")?.let{ ocupacionId ->

            if (ocupacionId != -1){
                viewModelScope.launch {
                    impl.getById(ocupacionId)?.also { ocupacion ->
                        currentUserId = ocupacion.ocupacionId

                        _descripcion.value = descripcion.value.copy(text = ocupacion.descripcion)
                        _salario.value = salario.value.copy(text = ocupacion.salario.toString())
                    }
                }
            }

        }
    }

    fun onEvent(event: OcupacionEditEvent){

        when(event){
            is OcupacionEditEvent.EnteredDescripcion -> {
                _descripcion.value = descripcion.value.copy(text = event.value)
            }

            is OcupacionEditEvent.EnteredSalario -> {
                _salario.value = salario.value.copy(text = event.value)
            }

            OcupacionEditEvent.InsertOcupacion -> {
                viewModelScope.launch {
                    impl.insert(
                        Ocupacion(
                            descripcion = descripcion.value.text,
                            salario = salario.value.text.toDouble(),
                            ocupacionId = currentUserId
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveOcupacion)
                }
            }
        }
    }

    sealed class UiEvent{
        object SaveOcupacion: UiEvent()
    }
}