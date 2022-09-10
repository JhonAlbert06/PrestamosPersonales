package edu.ucne.prestamospersonales.data.repositoryImpl

import edu.ucne.prestamospersonales.data.dao.OcupacionDao
import edu.ucne.prestamospersonales.data.model.Ocupacion
import edu.ucne.prestamospersonales.feature_Ocupaciones.domain.repository.OcupacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionRepositoryImpl @Inject constructor(
    private val dao: OcupacionDao
): OcupacionRepository {

    override suspend fun insert(Ocupacion: Ocupacion) {
        return dao.insert(Ocupacion)
    }

    override suspend fun delete(Ocupacion: Ocupacion) {
        return dao.delete(Ocupacion)
    }

    override fun getAll(): Flow<List<Ocupacion>> {
        return dao.getAll()
    }

    override suspend fun getById(id: Int): Ocupacion? {
        return dao.getById(id)
    }

}