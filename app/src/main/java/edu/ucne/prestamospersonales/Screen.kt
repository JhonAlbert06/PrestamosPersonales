package edu.ucne.prestamospersonales

sealed class Screen(val route: String){
    object HomePersona: Screen("homePersona")
    object EditPersona: Screen("editPersona?personaId={personaId}"){
        fun passId(personaId: Int?): String {
            return "editPersona?personaId=$personaId"
        }
    }
}
