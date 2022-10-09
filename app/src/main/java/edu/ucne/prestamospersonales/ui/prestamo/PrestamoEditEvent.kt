package edu.ucne.prestamospersonales.ui.prestamo

sealed class PrestamoEditEvent{
    data class EnteredFechaPrestamo(val value: String): PrestamoEditEvent()
    data class EnteredFechaVence(val value: String): PrestamoEditEvent()
    data class EnteredPersonaId(val value: String): PrestamoEditEvent()
    data class EnteredConcepto(val value: String): PrestamoEditEvent()
    data class EnteredMonto(val value: String): PrestamoEditEvent()
    data class EnteredBalance(val value: String): PrestamoEditEvent()
    object InsertPrestamo: PrestamoEditEvent()
}
