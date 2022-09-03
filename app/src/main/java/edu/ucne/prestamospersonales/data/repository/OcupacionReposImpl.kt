package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.local.dao.OcupacionDao
import edu.ucne.prestamospersonales.domain.model.Ocupacion
import edu.ucne.prestamospersonales.domain.repository.OcupacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionReposImpl @Inject constructor(
    private val dao: OcupacionDao
): OcupacionRepository{

    override suspend fun inserOcupacion(Ocupacion: Ocupacion) {
        return dao.inserOcupacion(Ocupacion)
    }

    override suspend fun deleteOcupacion(Ocupacion: Ocupacion) {
        return dao.deleteOcupacion(Ocupacion)
    }

    override fun getOcupaciones(): Flow<List<Ocupacion>> {
        return dao.getOcupaciones()
    }

    override suspend fun getOcupacionesById(id: Int): Ocupacion? {
        return dao.getOcupacionesById(id)
    }

}