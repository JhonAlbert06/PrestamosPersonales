package edu.ucne.prestamospersonales.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int? = 0,
    val nombres: String = "",
    val telefono: String = "",
    val celular: String = "",
    val email: String = "",
    val direccion: String = "",
    val fechaNacimiento: String = "",
    val ocupacionId: Int? = 0,
    val balance: Double = 0.0
)