package edu.ucne.prestamospersonales.data.remote

import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticuloRepository @Inject constructor(
    private val api: ArticulosApi
) {
    suspend fun getArticulos(): List<ArticulosResponseDto> {
        return withContext(Dispatchers.IO){
            val resonse = api.GetArticulos()
            resonse.body()?: emptyList()
        }
    }
}