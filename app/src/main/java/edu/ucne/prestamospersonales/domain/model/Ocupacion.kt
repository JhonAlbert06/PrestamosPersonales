package edu.ucne.prestamospersonales.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones")
data class Ocupacion(

    @PrimaryKey(autoGenerate = true)
    val OcupacionId: Int? = null,
    val Descripcion: String = "",
    val Salario: Double = 0.0

)
