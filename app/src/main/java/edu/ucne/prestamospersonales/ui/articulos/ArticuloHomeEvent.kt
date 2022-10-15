package edu.ucne.prestamospersonales.ui.articulos


sealed class ArticuloHomeEvent{
    data class  DeleteArticulo(val id: String) : ArticuloHomeEvent()
}
