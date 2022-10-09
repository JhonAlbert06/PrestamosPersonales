package edu.ucne.prestamospersonales.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.prestamospersonales.data.local.models.Persona
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(persona: Persona)

    @Delete
    suspend fun delete(persona: Persona)

    @Query("SELECT * FROM Personas")
    fun getAll(): Flow<List<Persona>>

    @Query("SELECT * FROM Personas WHERE personaId = :id")
    suspend fun getById(id:Int): Persona?
}