package edu.ucne.prestamospersonales.ui.prestamo

import edu.ucne.prestamospersonales.data.models.Prestamo

data class PrestamoHomeState(
    val prestamos: List<Prestamo> = emptyList()
)
