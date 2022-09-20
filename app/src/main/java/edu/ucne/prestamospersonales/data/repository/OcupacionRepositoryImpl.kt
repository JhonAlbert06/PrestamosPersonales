package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.dao.OcupacionDao
import edu.ucne.prestamospersonales.data.models.Ocupacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionRepositoryImpl @Inject constructor(
    private val dao: OcupacionDao
): OcupacionRepository{

    override suspend fun insert(ocupacion: Ocupacion) {
        dao.insert(ocupacion)
    }

    override suspend fun delete(ocupacion: Ocupacion) {
        dao.delete(ocupacion)
    }

    override suspend fun getById(id: Int): Ocupacion? {
        return dao.getById(id)
    }

    override fun getAll(): Flow<List<Ocupacion>> {
        return dao.getAll()
    }
}