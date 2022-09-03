package edu.ucne.prestamospersonales.presentation.home

import edu.ucne.prestamospersonales.domain.model.Ocupacion

sealed class HomeEvent{
    data class  DeleteOcupacion(val ocupacion: Ocupacion) : HomeEvent()
}
