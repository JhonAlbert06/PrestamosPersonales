package edu.ucne.prestamospersonales.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.prestamospersonales.data.dao.OcupacionDao
import edu.ucne.prestamospersonales.data.model.Ocupacion

@Database(
    entities = [Ocupacion::class],
    version = 1
)

abstract class OcupacionDB : RoomDatabase() {
    abstract val ocupacionDao: OcupacionDao
}