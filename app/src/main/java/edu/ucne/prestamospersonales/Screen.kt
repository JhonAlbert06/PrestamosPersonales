package edu.ucne.prestamospersonales

sealed class Screen(val route: String){

    object HomeOcupacion: Screen("OcupacionHomeScreen")
    object EditOcupacion: Screen("OcupacionEditScreen?ocupacionId={ocupacionId}"){
        fun passId(ocupacionId: Int?): String {
            return "OcupacionEditScreen?ocupacionId=$ocupacionId"
        }
    }

    object HomePersona: Screen("PersonaHomeScreen")
    object EditPersona: Screen("PersonaEditScreen?personaId={personaId}"){
        fun passId(personaId: Int?): String {
            return "PersonaEditScreen?personaId=$personaId"
        }
    }

}

/*
    object EditPrestamo: Screen("editPrestamo?prestamoId={prestamoId}"){
        fun passId(prestamoId: Int?): String {
            return "editPrestamo?prestamoId=$prestamoId"
        }
    }
    */