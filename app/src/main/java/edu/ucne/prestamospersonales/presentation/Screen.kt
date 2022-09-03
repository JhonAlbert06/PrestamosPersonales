package edu.ucne.prestamospersonales.presentation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Edit: Screen("edit?OcupacionId={OcupacionId}"){
        fun passId(OcupacionId: Int?): String {
            return "edit?OcupacionId=$OcupacionId"
        }
    }
}
