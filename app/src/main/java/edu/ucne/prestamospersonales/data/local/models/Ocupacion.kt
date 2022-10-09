package edu.ucne.prestamospersonales.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones")
data class Ocupacion(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int? = null,
    val descripcion: String = "",
    val salario: Double = 0.0
)
