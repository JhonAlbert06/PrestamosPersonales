package edu.ucne.prestamospersonales.feature_personas.domain.repository

import edu.ucne.prestamospersonales.data.model.Persona
import kotlinx.coroutines.flow.Flow

interface PersonaRepository {
    suspend fun insert(persona: Persona)
    suspend fun delete(persona: Persona)
    suspend fun getById(id:Int): Persona?
    fun getAll(): Flow<List<Persona>>
}