package edu.ucne.prestamospersonales.ui.prestamo

import edu.ucne.prestamospersonales.data.local.models.Prestamo

sealed class PrestamoHomeEvent{
    data class DeletePrestamo(val prestamo: Prestamo) : PrestamoHomeEvent()
}
