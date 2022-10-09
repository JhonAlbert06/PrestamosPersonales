package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.local.models.Ocupacion
import kotlinx.coroutines.flow.Flow

interface OcupacionRepository {
    suspend fun insert(ocupacion: Ocupacion)
    suspend fun delete(ocupacion: Ocupacion)
    suspend fun getById(id:Int): Ocupacion?
    fun getAll(): Flow<List<Ocupacion>>
}