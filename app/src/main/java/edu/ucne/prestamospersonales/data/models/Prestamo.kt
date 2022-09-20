package edu.ucne.prestamospersonales.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prestamos")
data class Prestamo(
    @PrimaryKey(autoGenerate = true)
    val prestamoId: Int? = null,
    val fechaPrestamo: String = "",
    val fechaVence: String = "",
    val personaId: Int? = null,
    val concepto: String = "",
    val monto: Double = 0.0,
    val Balance: Double = 0.0
)
