package edu.ucne.prestamospersonales.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int? = 0,
    val nombres: String = "",
    val telefono: Long = 0,
    val celular: Long = 0,
    val email: String = "",
    val direccion: String = "",
    val fechaNacimiento: String = "",
    val ocupacionId: Int? = 0,
)
