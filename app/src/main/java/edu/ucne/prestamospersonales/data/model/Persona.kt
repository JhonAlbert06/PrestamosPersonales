package edu.ucne.prestamospersonales.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int? = null,
    val nombres: String = "",
    val telefono: Int = 0,
    val Celular: Int = 0,
    val Email: String = "",
    val Direccion: String = "",
    val fechaNacimiento: String = "",
    val ocupacionId: Int? = null,
)
