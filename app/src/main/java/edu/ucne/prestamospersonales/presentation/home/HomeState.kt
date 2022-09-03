package edu.ucne.prestamospersonales.presentation.home

import edu.ucne.prestamospersonales.domain.model.Ocupacion

data class HomeState (
    val ocupaciones: List<Ocupacion> = emptyList()
)