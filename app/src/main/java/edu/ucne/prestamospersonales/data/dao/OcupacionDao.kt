package edu.ucne.prestamospersonales.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.prestamospersonales.data.models.Ocupacion
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ocupacion: Ocupacion)

    @Delete
    suspend fun delete(ocupacion: Ocupacion)

    @Query("SELECT * FROM Ocupaciones")
    fun getAll(): Flow<List<Ocupacion>>

    @Query("SELECT * FROM Ocupaciones WHERE ocupacionId = :id")
    suspend fun getById(id:Int): Ocupacion?
}