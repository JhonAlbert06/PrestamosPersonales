package edu.ucne.prestamospersonales.ui.persona

import edu.ucne.prestamospersonales.data.models.Persona

data class PersonaHomeState(
    val personas: List<Persona> = emptyList()
)
