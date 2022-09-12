package edu.ucne.prestamospersonales.data

import androidx.room.*
import edu.ucne.prestamospersonales.data.dao.*
import edu.ucne.prestamospersonales.data.model.*

@Database(
    entities = [Persona::class],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract val personaDao : PersonaDao
}