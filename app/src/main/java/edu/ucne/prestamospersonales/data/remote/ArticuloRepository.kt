package edu.ucne.prestamospersonales.data.remote

import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticuloRepository @Inject constructor(
    private val api: ArticulosApi
) {
    suspend fun getAll(): Flow<List<ArticulosResponseDto>> = flow{
        val articulos = api.GetAll()
    }
}