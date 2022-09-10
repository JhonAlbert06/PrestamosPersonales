package edu.ucne.prestamospersonales.feature_personas.domain.repository

import edu.ucne.prestamospersonales.data.dao.PersonaDao
import edu.ucne.prestamospersonales.data.model.Persona
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonaRepositoryImpl @Inject constructor(
    private val dao: PersonaDao
): PersonaRepository {

    override suspend fun insert(persona: Persona) {
        dao.insert(persona)
    }

    override suspend fun delete(persona: Persona) {
        dao.delete(persona)
    }

    override suspend fun getById(id: Int): Persona? {
        return dao.getById(id)
    }

    override fun getAll(): Flow<List<Persona>> {
         return dao.getAll()
    }

}