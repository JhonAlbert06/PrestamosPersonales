package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.local.AppDataBase
import edu.ucne.prestamospersonales.data.local.models.Prestamo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrestamoRepositoryImpl @Inject constructor(
    private val db: AppDataBase
):PrestamoRepository {

    override suspend fun insert(prestamo: Prestamo) {
        db.prestamoDao.insert(prestamo)
    }

    override suspend fun delete(prestamo: Prestamo) {
        db.prestamoDao.delete(prestamo)
    }

    override suspend fun getById(id: Int): Prestamo? {
        return db.prestamoDao.getById(id)
    }

    override fun getAll(): Flow<List<Prestamo>> {
        return db.prestamoDao.getAll()
    }

   /* override suspend fun findPersona(id: Int): String? {
        return db.prestamoDao.findPersona(id)
    }*/


}