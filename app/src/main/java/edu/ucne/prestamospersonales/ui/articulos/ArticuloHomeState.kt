package edu.ucne.prestamospersonales.ui.articulos

import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto

data class ArticuloHomeState(
    val isLoading: Boolean = false,
    val articulos: List<ArticulosResponseDto> = emptyList(),
    val error: String = ""
)
