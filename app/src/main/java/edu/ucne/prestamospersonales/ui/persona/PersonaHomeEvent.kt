package edu.ucne.prestamospersonales.ui.persona

import edu.ucne.prestamospersonales.data.models.Persona

sealed class PersonaHomeEvent{
    data class DeletePersona(val persona: Persona) : PersonaHomeEvent()
}
