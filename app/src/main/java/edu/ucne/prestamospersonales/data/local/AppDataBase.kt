package edu.ucne.prestamospersonales.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.prestamospersonales.data.local.dao.OcupacionDao
import edu.ucne.prestamospersonales.data.local.dao.PersonaDao
import edu.ucne.prestamospersonales.data.local.dao.PrestamoDao
import edu.ucne.prestamospersonales.data.local.models.Ocupacion
import edu.ucne.prestamospersonales.data.local.models.Persona
import edu.ucne.prestamospersonales.data.local.models.Prestamo

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