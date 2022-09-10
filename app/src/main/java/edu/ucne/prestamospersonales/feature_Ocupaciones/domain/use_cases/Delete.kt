package edu.ucne.prestamospersonales.feature_Ocupaciones.domain.use_cases

import edu.ucne.prestamospersonales.data.model.Ocupacion
import edu.ucne.prestamospersonales.feature_Ocupaciones.domain.repository.OcupacionRepository
import javax.inject.Inject

class Delete @Inject constructor(
    private val repository: OcupacionRepository
) {
    suspend operator fun invoke(ocupacion: Ocupacion){
        repository.delete(ocupacion)
    }
}