package edu.ucne.prestamospersonales.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.prestamospersonales.data.local.dao.OcupacionDao
import edu.ucne.prestamospersonales.domain.model.Ocupacion

@Database(
    entities = [Ocupacion::class],
    version = 1,
    exportSchema = false
)

abstract class OcupacionDB : RoomDatabase() {
    abstract val ocupacionDao: OcupacionDao
}