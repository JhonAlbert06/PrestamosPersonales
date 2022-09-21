package edu.ucne.prestamospersonales

sealed class Screen(val route: String){
    /*object EditOcupacion: Screen("editOcupacion?ocupacionId={ocupacionId}"){
        fun passId(ocupacionId: Int?): String {
            return "editOcupacion?ocupacionId=$ocupacionId"
        }
    }*/
    /*object EditPersona: Screen("editPersona?personaId={personaId}"){
        fun passId(personaId: Int?): String {
            return "editPersona?personaId=$personaId"
        }
    }*/
    object HomePrestamo: Screen("homePersona")
    object EditPrestamo: Screen("editPrestamo?prestamoId={prestamoId}"){
        fun passId(prestamoId: Int?): String {
            return "editPrestamo?prestamoId=$prestamoId"
        }
    }
}
