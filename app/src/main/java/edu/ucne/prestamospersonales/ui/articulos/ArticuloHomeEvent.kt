package edu.ucne.prestamospersonales.ui.articulos

import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto

sealed class ArticuloHomeEvent{
    data class  DeleteArticulo(val articulo: ArticulosResponseDto) : ArticuloHomeEvent()
}
