package edu.ucne.prestamospersonales

sealed class Screen(val route: String){
    object EditOcupacion: Screen("editOcupacion?ocupacionId={ocupacionId}"){
        fun passId(ocupacionId: Int?): String {
            return "editOcupacion?ocupacionId=$ocupacionId"
        }
    }
}
