package edu.ucne.prestamospersonales.data.dao

import androidx.room.*
import edu.ucne.prestamospersonales.data.model.Ocupacion
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