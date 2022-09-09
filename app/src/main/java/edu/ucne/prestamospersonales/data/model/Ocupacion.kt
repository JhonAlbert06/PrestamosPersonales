package edu.ucne.prestamospersonales.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones")
data class Ocupacion(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int? = 0,
    val descripcion: String = "",
    val salario: Double = 0.0
)
