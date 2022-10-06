package edu.ucne.prestamospersonales.presentation.registros.editPrestamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.models.Prestamo
import edu.ucne.prestamospersonales.data.repository.PrestamoRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPrestamoViewModel @Inject constructor(
    private val Impl: PrestamoRepositoryImpl
): ViewModel() {

    var isError by mutableStateOf(false)

    var fechaPrestamo by mutableStateOf("")
    var fechaVence by mutableStateOf("")
    var personaId by mutableStateOf("")
    var concepto by mutableStateOf("")
    var monto by mutableStateOf("")
    var balance by mutableStateOf("")

    fun save() {
        viewModelScope.launch {
            Impl.insert(
                prestamo = Prestamo(
                    fechaPrestamo = fechaPrestamo,
                    fechaVence = fechaVence,
                    personaId = personaId.toInt(),
                    concepto = concepto,
                    monto = monto.toDouble(),
                    balance = balance.toDouble()
                )
            )
        }
    }
}