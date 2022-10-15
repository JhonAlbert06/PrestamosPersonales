package edu.ucne.prestamospersonales.ui.articulos

sealed class ArticuloEditEvent {
    data class EnteredDescripcion(val value: String): ArticuloEditEvent()
    data class EnteredMarca(val value: String): ArticuloEditEvent()
    data class EnteredPrecio(val value: String): ArticuloEditEvent()
    data class EnteredExistencia(val value: String): ArticuloEditEvent()
    object InsertArticulo: ArticuloEditEvent()
}