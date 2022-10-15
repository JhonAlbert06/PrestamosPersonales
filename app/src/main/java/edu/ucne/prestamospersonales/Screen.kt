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

    object HomePrestamo: Screen("PrestamoHomeScreen")
    object EditPrestamo: Screen("PrestamoEditScreen?prestamoId={prestamoId}"){
        fun passId(prestamoId: Int?): String {
            return "PrestamoEditScreen?prestamoId=$prestamoId"
        }
    }

    object PrincipalScreen: Screen("PrincipalScreen")

    object HomeArticulo: Screen("ArticuloHomeScreen")
    object EditArticulo: Screen("ArticuloEditScreen?ariticuloId={ariticuloId}"){
        fun passId(ariticuloId: String?): String {
            return "ArticuloEditScreen?ariticuloId=$ariticuloId"
        }
    }
}