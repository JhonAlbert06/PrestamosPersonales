package edu.ucne.prestamospersonales.ui.prestamo

import edu.ucne.prestamospersonales.data.local.models.Prestamo

data class PrestamoHomeState(
    val prestamos: List<Prestamo> = emptyList()
)
