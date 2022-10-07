package edu.ucne.prestamospersonales.ui.ocupaciones

import edu.ucne.prestamospersonales.data.models.Ocupacion

data class OcupacionHomeState(
    val ocupaciones: List<Ocupacion> = emptyList()
)
