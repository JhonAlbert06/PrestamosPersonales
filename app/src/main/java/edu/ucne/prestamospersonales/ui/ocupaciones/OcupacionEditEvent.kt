package edu.ucne.prestamospersonales.ui.ocupaciones

sealed class OcupacionEditEvent{
    data class EnteredDescripcion(val value: String): OcupacionEditEvent()
    data class EnteredSalario(val value: String): OcupacionEditEvent()
    object InsertOcupacion: OcupacionEditEvent()
}
