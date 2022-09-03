package edu.ucne.prestamospersonales.domain.use_cases

import edu.ucne.prestamospersonales.domain.model.Ocupacion
import edu.ucne.prestamospersonales.domain.repository.OcupacionRepository
import javax.inject.Inject

class DeleteOcupacion @Inject constructor(
    private val repository: OcupacionRepository
) {
    suspend operator fun invoke(ocupacion: Ocupacion){
        repository.deleteOcupacion(ocupacion)
    }
}