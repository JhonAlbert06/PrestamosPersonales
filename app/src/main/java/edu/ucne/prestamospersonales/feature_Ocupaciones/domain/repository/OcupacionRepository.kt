package edu.ucne.prestamospersonales.feature_Ocupaciones.domain.repository

import edu.ucne.prestamospersonales.data.model.Ocupacion
import kotlinx.coroutines.flow.Flow

interface OcupacionRepository {

    suspend fun inser(ocupacion: Ocupacion)

    suspend fun delete(ocupacion: Ocupacion)

    suspend fun getById(id:Int): Ocupacion?

    fun getAll(): Flow<List<Ocupacion>>
}