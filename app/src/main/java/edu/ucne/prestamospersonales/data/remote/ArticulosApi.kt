package edu.ucne.prestamospersonales.data.remote

import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ArticulosApi{
    @GET("api/Articulos")
    suspend fun GetArticulos(): Response<List<ArticulosResponseDto>>
}