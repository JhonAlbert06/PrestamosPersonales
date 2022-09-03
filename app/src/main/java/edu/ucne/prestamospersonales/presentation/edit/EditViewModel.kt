package edu.ucne.prestamospersonales.presentation.edit


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.domain.model.Ocupacion
import edu.ucne.prestamospersonales.domain.use_cases.GetOcupacion
import edu.ucne.prestamospersonales.domain.use_cases.InsertOcupacion
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getOcupacion: GetOcupacion,
    private var insertOcupacion: InsertOcupacion,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _descripcion = mutableStateOf(TextFieldState())
    val descripcion: State<TextFieldState> = _descripcion

    private val _salario = mutableStateOf(TextFieldState())
    val salario: State<TextFieldState> = _salario

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentUserId: Int? = null

    init {
        savedStateHandle.get<Int>("OcupacionId")?.let {OcupacionId ->
            if (OcupacionId != -1){
                viewModelScope.launch {
                    getOcupacion(OcupacionId)?.also{ ocupacion ->
                        currentUserId = ocupacion.OcupacionId

                        _descripcion.value = descripcion.value.copy(
                            text = ocupacion.Descripcion
                        )

                        _salario.value = salario.value.copy(
                            text = ocupacion.Salario.toString()
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: EditEvent){
        when(event){
            is EditEvent.EnteredDescripcion -> {
                _descripcion.value = descripcion.value.copy(
                    text = event.value
                )
            }

            is EditEvent.EnteredSalario -> {
                _salario.value = salario.value.copy(
                    text = event.value
                )
            }

            EditEvent.InsertOcupacion -> {
                viewModelScope.launch {
                    insertOcupacion(
                        Ocupacion(
                            Descripcion = descripcion.value.text,
                            Salario = salario.value.text.toDouble(),
                            OcupacionId = currentUserId
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