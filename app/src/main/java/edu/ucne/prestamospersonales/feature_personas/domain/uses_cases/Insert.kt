package edu.ucne.prestamospersonales.feature_personas.domain.uses_cases

import edu.ucne.prestamospersonales.data.model.Persona
import edu.ucne.prestamospersonales.feature_personas.domain.repository.PersonaRepository
import javax.inject.Inject

class Insert @Inject constructor(
    private val repository: PersonaRepository
){
    suspend operator fun invoke(persona: Persona){
        repository.insert(persona)
    }
}