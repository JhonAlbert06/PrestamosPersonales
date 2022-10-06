package edu.ucne.prestamospersonales.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.prestamospersonales.data.dao.OcupacionDao
import edu.ucne.prestamospersonales.data.dao.PersonaDao
import edu.ucne.prestamospersonales.data.dao.PrestamoDao
import edu.ucne.prestamospersonales.data.models.Ocupacion
import edu.ucne.prestamospersonales.data.models.Persona
import edu.ucne.prestamospersonales.data.models.Prestamo

@Database(
    entities = [
        Ocupacion::class,
        Persona::class,
        Prestamo::class],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase: RoomDatabase() {
    abstract val ocupacionDao: OcupacionDao
    abstract val personaDao: PersonaDao
    abstract val prestamoDao: PrestamoDao
}