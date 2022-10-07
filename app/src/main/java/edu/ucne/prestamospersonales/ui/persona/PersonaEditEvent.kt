package edu.ucne.prestamospersonales.ui.persona

sealed class PersonaEditEvent{
    data class EnteredNombres(val value: String): PersonaEditEvent()
    data class EnteredTelefono(val value: String): PersonaEditEvent()
    data class EnteredCelular(val value: String): PersonaEditEvent()
    data class EnteredEmail(val value: String): PersonaEditEvent()
    data class EnteredDireccion(val value: String): PersonaEditEvent()
    data class EnteredFechaNacimiento(val value: String): PersonaEditEvent()
    data class EnteredOculacionId(val value: String): PersonaEditEvent()
    data class EnteredBalance(val value: String): PersonaEditEvent()
    object InsertPersona: PersonaEditEvent()
}
