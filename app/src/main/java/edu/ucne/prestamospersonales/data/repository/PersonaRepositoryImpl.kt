package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.local.AppDataBase
import edu.ucne.prestamospersonales.data.local.models.Persona
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonaRepositoryImpl @Inject constructor(
    private val db: AppDataBase
): PersonaRepository{

    override suspend fun insert(persona: Persona) {
        db.personaDao.insert(persona)
    }

    override suspend fun delete(persona: Persona) {
        db.personaDao.delete(persona)
    }

    override suspend fun getById(id: Int): Persona? {
        return db.personaDao.getById(id)
    }

    override fun getAll(): Flow<List<Persona>> {
        return db.personaDao.getAll()
    }

}