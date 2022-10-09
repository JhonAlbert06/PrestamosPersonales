package edu.ucne.prestamospersonales.ui.ocupaciones

import edu.ucne.prestamospersonales.data.local.models.Ocupacion

data class OcupacionHomeState(
    val ocupaciones: List<Ocupacion> = emptyList()
)
