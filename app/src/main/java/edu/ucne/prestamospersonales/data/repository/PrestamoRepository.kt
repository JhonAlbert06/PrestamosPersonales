package edu.ucne.prestamospersonales.data.repository

import edu.ucne.prestamospersonales.data.local.models.Prestamo
import kotlinx.coroutines.flow.Flow

interface PrestamoRepository {
    suspend fun insert(prestamo: Prestamo)
    suspend fun delete(prestamo: Prestamo)
    suspend fun getById(id:Int): Prestamo?
    fun getAll(): Flow<List<Prestamo>>
}