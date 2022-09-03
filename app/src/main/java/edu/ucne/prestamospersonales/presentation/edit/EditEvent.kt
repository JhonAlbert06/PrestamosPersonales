package edu.ucne.prestamospersonales.presentation.edit

sealed class EditEvent {
    data class EnteredDescripcion(val value: String): EditEvent()
    data class EnteredSalario(val value: String): EditEvent()
    object InsertOcupacion: EditEvent()
}