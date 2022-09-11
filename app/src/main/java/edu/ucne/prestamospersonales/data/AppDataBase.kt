package edu.ucne.prestamospersonales.data

import androidx.room.*
import edu.ucne.prestamospersonales.data.dao.PersonaDao
import edu.ucne.prestamospersonales.data.model.Persona

@Database(
    entities = [Persona::class],
    version = 1
)

abstract class AppDataBase : RoomDatabase() {
    abstract val personaDao : PersonaDao
}