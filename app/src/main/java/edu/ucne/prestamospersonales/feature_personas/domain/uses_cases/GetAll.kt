package edu.ucne.prestamospersonales.feature_personas.domain.uses_cases

import edu.ucne.prestamospersonales.data.model.Persona
import edu.ucne.prestamospersonales.feature_personas.domain.repository.PersonaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAll @Inject constructor(
    private val repository: PersonaRepository
) {
    operator fun invoke(): Flow<List<Persona>> {
        return repository.getAll()
    }
}