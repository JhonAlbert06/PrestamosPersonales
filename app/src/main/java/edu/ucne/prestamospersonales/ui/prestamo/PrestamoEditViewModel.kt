package edu.ucne.prestamospersonales.ui.prestamo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Prestamo
import edu.ucne.prestamospersonales.data.repository.PrestamoRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrestamoEditViewModel @Inject constructor(
    private val impl: PrestamoRepositoryImpl,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _fechaPrestamo = mutableStateOf(TextFieldPrestamoState())
    val fechaPrestamo: State<TextFieldPrestamoState> = _fechaPrestamo

    private val _fechaVence = mutableStateOf(TextFieldPrestamoState())
    val fechaVence: State<TextFieldPrestamoState> = _fechaVence

    private val _personaId = mutableStateOf(TextFieldPrestamoState())
    val personaId: State<TextFieldPrestamoState> = _personaId

    private val _concepto = mutableStateOf(TextFieldPrestamoState())
    val concepto: State<TextFieldPrestamoState> = _concepto

    private val _monto = mutableStateOf(TextFieldPrestamoState())
    val monto: State<TextFieldPrestamoState> = _monto

    private val _balance = mutableStateOf(TextFieldPrestamoState())
    val balance: State<TextFieldPrestamoState> = _balance

    var isError = false

    private var currentUserId: Int? = null

    private val _eventFlow = MutableSharedFlow<PrestamoEditViewModel.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("prestamoId")?.let{ prestamoId ->

            if (prestamoId != -1){
                viewModelScope.launch {
                    impl.getById(prestamoId)?.also { prestamo ->
                        currentUserId = prestamo.prestamoId

                        _fechaPrestamo.value = fechaPrestamo.value.copy(text = prestamo.fechaPrestamo)
                        _fechaVence.value = fechaVence.value.copy(text = prestamo.fechaVence)
                        _personaId.value = personaId.value.copy(text = prestamo.personaId.toString())
                        _concepto.value = concepto.value.copy(text = prestamo.concepto)
                        _monto.value = monto.value.copy(text = prestamo.monto.toString())
                        _balance.value = balance.value.copy(text = prestamo.balance.toString())
                    }
                }
            }

        }
    }

    fun onEvent(event: PrestamoEditEvent){

        when(event){
            is PrestamoEditEvent.EnteredFechaPrestamo -> {
                _fechaPrestamo.value = fechaPrestamo.value.copy(text = event.value)
            }

            is PrestamoEditEvent.EnteredFechaVence -> {
                _fechaVence.value = fechaVence.value.copy(text = event.value)
            }

            is PrestamoEditEvent.EnteredPersonaId -> {
                _personaId.value = personaId.value.copy(text = event.value)
            }

            is PrestamoEditEvent.EnteredConcepto -> {
                _concepto.value = concepto.value.copy(text = event.value)
            }

            is PrestamoEditEvent.EnteredMonto -> {
                _monto.value = monto.value.copy(text = event.value)
            }

            is PrestamoEditEvent.EnteredBalance -> {
                _balance.value = balance.value.copy(text = event.value)
            }

            PrestamoEditEvent.InsertPrestamo -> {
                viewModelScope.launch {
                    impl.insert(
                        Prestamo(
                            fechaPrestamo = _fechaPrestamo.value.text,
                            fechaVence = _fechaVence.value.text,
                            personaId = _personaId.value.text.toInt(),
                            concepto = _concepto.value.text,
                            monto = _monto.value.text.toDouble(),
                            balance = _balance.value.text.toDouble(),
                            prestamoId = currentUserId
                        )
                    )
                    _eventFlow.emit(UiEvent.SavePrestamo)
                }
            }
        }
    }

    sealed class UiEvent{
        object SavePrestamo: UiEvent()
    }

}