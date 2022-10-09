package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.local.AppDataBase
import edu.ucne.prestamospersonales.data.local.models.Ocupacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionRepositoryImpl @Inject constructor(
    private val db: AppDataBase
): OcupacionRepository{

    override suspend fun insert(ocupacion: Ocupacion) {
        db.ocupacionDao.insert(ocupacion)
    }

    override suspend fun delete(ocupacion: Ocupacion) {
        db.ocupacionDao.delete(ocupacion)
    }

    override suspend fun getById(id: Int): Ocupacion? {
        return db.ocupacionDao.getById(id)
    }

    override fun getAll(): Flow<List<Ocupacion>> {
        return db.ocupacionDao.getAll()
    }
}