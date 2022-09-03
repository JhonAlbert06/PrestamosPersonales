package edu.ucne.prestamospersonales.domain.use_cases

import edu.ucne.prestamospersonales.domain.model.Ocupacion
import edu.ucne.prestamospersonales.domain.repository.OcupacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOcupaciones @Inject constructor(
    private val repository: OcupacionRepository
) {
    operator fun invoke(): Flow<List<Ocupacion>>{
        return repository.getOcupaciones()
    }
}