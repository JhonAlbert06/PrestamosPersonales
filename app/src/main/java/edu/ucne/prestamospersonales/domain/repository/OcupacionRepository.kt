package edu.ucne.prestamospersonales.domain.repository

import edu.ucne.prestamospersonales.domain.model.Ocupacion
import kotlinx.coroutines.flow.Flow

interface OcupacionRepository {

    suspend fun inserOcupacion(Ocupacion: Ocupacion)

    suspend fun deleteOcupacion(Ocupacion: Ocupacion)

    suspend fun getOcupacionesById(id:Int): Ocupacion?

    fun getOcupaciones(): Flow<List<Ocupacion>>

}