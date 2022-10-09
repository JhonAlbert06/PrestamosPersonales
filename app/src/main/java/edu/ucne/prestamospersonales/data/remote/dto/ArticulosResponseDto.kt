package edu.ucne.prestamospersonales.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticulosResponseDto(
    val ariticuloId: String = "",
    val descripcion: String = "",
    val marca: String = "",
    val precio: String = "",
    val existencia: String = ""
)
