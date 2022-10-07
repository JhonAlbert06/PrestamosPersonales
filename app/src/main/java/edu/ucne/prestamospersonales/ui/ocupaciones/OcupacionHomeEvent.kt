package edu.ucne.prestamospersonales.ui.ocupaciones

import edu.ucne.prestamospersonales.data.models.Ocupacion

sealed class OcupacionHomeEvent{
    data class  DeleteOcupacion(val ocupacion: Ocupacion) : OcupacionHomeEvent()
}
