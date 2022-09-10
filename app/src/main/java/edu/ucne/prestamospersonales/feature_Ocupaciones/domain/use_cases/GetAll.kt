package edu.ucne.prestamospersonales.feature_Ocupaciones.domain.use_cases

import edu.ucne.prestamospersonales.data.model.Ocupacion
import edu.ucne.prestamospersonales.feature_Ocupaciones.domain.repository.OcupacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAll @Inject constructor(
    private val repository: OcupacionRepository
) {
    operator  fun invoke(): Flow<List<Ocupacion>> {
        return repository.getAll()
    }
}