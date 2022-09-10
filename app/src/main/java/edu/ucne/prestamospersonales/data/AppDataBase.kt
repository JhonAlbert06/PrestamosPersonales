package edu.ucne.prestamospersonales.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.prestamospersonales.data.dao.PersonaDao
import edu.ucne.prestamospersonales.data.model.Persona

@Database(
    entities = [Persona::class],
    version = 1
)

abstract class AppDataBase : RoomDatabase() {
    abstract val personaDao : PersonaDao
}