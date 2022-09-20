package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.dao.PrestamoDao
import edu.ucne.prestamospersonales.data.models.Prestamo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrestamoRepositoryImpl @Inject constructor(
    private val dao: PrestamoDao
):PrestamoRepository {

    override suspend fun insert(prestamo: Prestamo) {
        dao.insert(prestamo)
    }

    override suspend fun delete(prestamo: Prestamo) {
        dao.delete(prestamo)
    }

    override suspend fun getById(id: Int): Prestamo? {
        return dao.getById(id)
    }

    override fun getAll(): Flow<List<Prestamo>> {
        return dao.getAll()
    }

}