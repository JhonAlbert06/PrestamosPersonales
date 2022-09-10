package edu.ucne.prestamospersonales

sealed class Screen(val route: String){
    object Consulta: Screen("consulta")
    object Registro: Screen("registro?OcupacionId={OcupacionId}"){
        fun passId(OcupacionId: Int?): String {
            return "registro?OcupacionId=$OcupacionId"
        }
    }
}
